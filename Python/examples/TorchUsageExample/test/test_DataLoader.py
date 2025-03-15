import torchvision
from torch.utils.data import DataLoader
from torch.utils.tensorboard.writer import SummaryWriter

test_data = torchvision.datasets.CIFAR10("../CIFAR10", False, transform=torchvision.transforms.ToTensor())
test_loader = DataLoader(test_data, 64, True, num_workers=0, drop_last=False)

# img,target=test_data[0]
# print(img.shape)
# print(target)

writer = SummaryWriter("../logs")
for epoch in range(2):
    step = 0
    for data in test_loader:
        imgs, targets = data
        # print(imgs.shape)
        # print(targets)
        writer.add_images("Epoch: {}".format(epoch), imgs, step)
        step += 1
writer.close()
