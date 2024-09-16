import torchvision
from torch.utils.tensorboard.writer import SummaryWriter

dataset_transfrom = torchvision.transforms.Compose([torchvision.transforms.ToTensor()])

train_set = torchvision.datasets.CIFAR10(root="../CIFAR10", train=True, transform=dataset_transfrom, download=True)
test_set = torchvision.datasets.CIFAR10(root="../CIFAR10", train=False, transform=dataset_transfrom, download=True)

# print(test_set[0])
# print(test_set.classes[test_set[0][1]])

writer = SummaryWriter("../logs")
for i in range(10):
    img, target = test_set[i]
    writer.add_image("test_set", img, i)

writer.close()
