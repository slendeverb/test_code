from torch import nn
import torch
import torchvision
from torch.utils.data import DataLoader
from torch.utils.tensorboard.writer import SummaryWriter

from model import Tudui

# 定义训练的设备
device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")

# CIFAR-10对VGG-16来说规模过小, 容易产生过拟合
# 准备数据集
train_data = torchvision.datasets.CIFAR10(
    root="../CIFAR10",
    train=True,
    download=True,
    transform=torchvision.transforms.ToTensor(),
)

test_data = torchvision.datasets.CIFAR10(
    root="../CIFAR10",
    train=False,
    download=True,
    transform=torchvision.transforms.ToTensor(),
)

# length
train_data_size = len(train_data)
test_data_size = len(test_data)

print(f"训练数据集的长度为: {train_data_size}")
print(f"测试数据集的长度为: {test_data_size}")

# 利用 DataLoader 加载数据集
train_dataloader = DataLoader(train_data, batch_size=64, shuffle=True, drop_last=True)
test_dataloader = DataLoader(test_data, batch_size=64, shuffle=True, drop_last=True)

# 创建网络模型
vgg16_false = torchvision.models.vgg16(weights=None)
torch.save(vgg16_false, "../models/vgg16/not_trained/vgg16_false.pth")
model = torch.load("../models/vgg16/not_trained/vgg16_false.pth",weights_only=False)
model.avgpool = torch.nn.AdaptiveAvgPool2d((1, 1))
model.classifier[0] = torch.nn.Linear(512, 4096)
model.classifier[6] = torch.nn.Linear(4096, 10)
# model = Tudui()
model.to(device)

print(model)

# 损失函数
loss_fn = nn.CrossEntropyLoss()
loss_fn.to(device)

# 优化器
learning_rate = 1e-3
optimizer = torch.optim.SGD(model.parameters(), lr=learning_rate, momentum=0.9)

# 设置训练网络的一些参数
# 记录训练的次数
total_train_step = 0
# 记录测试的次数
total_test_step = 0
# 训练的轮数
epochs = 1000

# 添加tensorboard
writer = SummaryWriter(log_dir="../logs_train")

for epoch in range(epochs):
    print(f"----------第 {epoch + 1} 轮训练开始----------")

    # 训练步骤开始
    model.train()
    for data in train_dataloader:
        optimizer.zero_grad()
        imgs, targets = data
        imgs = imgs.to(device)
        targets = targets.to(device)
        outputs = model(imgs)
        loss = loss_fn(outputs, targets)
        loss.backward()
        optimizer.step()

        total_train_step += 1
        if total_train_step % 100 == 0:
            print(f"训练次数: {total_train_step}, Loss: {loss.item()}")
            writer.add_scalar("train_loss", loss.item(), total_train_step)

    # 测试步骤开始
    model.eval()
    total_test_loss = 0
    total_accuracy = 0
    with torch.no_grad():
        for data in test_dataloader:
            imgs, targets = data
            imgs = imgs.to(device)
            targets = targets.to(device)
            outputs = model(imgs)
            loss = loss_fn(outputs, targets)
            total_test_loss += loss.item()
            accuracy = outputs.argmax(axis=1).eq(targets).sum().item()
            total_accuracy += accuracy

    print(f"整体测试集上的Loss: {total_test_loss}")
    print(f"整体测试集上的正确率: {total_accuracy / test_data_size}")
    writer.add_scalar("test_loss", total_test_loss, total_test_step)
    writer.add_scalar("test_accuracy", total_accuracy / test_data_size, total_test_step)
    total_test_step += 1

    if (epoch + 1) % 10 == 0:
        torch.save(model, f"../models/vgg16/trained/vgg16_{epoch}.pth")
        # torch.save(model, f'../models/tudui/tudui_{epoch}.pth')
        print("模型已保存")

writer.close()
