import torchvision.datasets
from torch import nn
from torchvision import models

# train_dataset = torchvision.datasets.ImageNet("../ImageNet/train", split='train', download=True,
#                                               transform=torchvision.transforms.ToTensor())

# vgg16_false = models.vgg16(weights="DEFAULT")
vgg16_true = models.vgg16(weights="DEFAULT")

print(vgg16_true)

train_data = torchvision.datasets.CIFAR10(root="../CIFAR10", train=True, download=True,
                                          transform=torchvision.transforms.ToTensor())

# vgg16_true.classifier.add_module("add_linear",nn.Linear(1000, 10))

vgg16_true.classifier[6] = nn.Linear(in_features=4096, out_features=10)

print(vgg16_true)
