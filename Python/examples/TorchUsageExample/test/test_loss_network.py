import torchvision.datasets
from torch import nn
from torch.utils.data import DataLoader

dataset = torchvision.datasets.CIFAR10(root="../CIFAR10", train=False, transform=torchvision.transforms.ToTensor(),
                                       download=True)

dataloader = DataLoader(dataset, batch_size=64, shuffle=True, drop_last=True)


class Tudui(nn.Module):
    def __init__(self):
        super(Tudui, self).__init__()
        self.model1 = nn.Sequential(
            nn.Conv2d(3, 32, 5, padding=2, stride=1),
            nn.MaxPool2d(2, 2),
            nn.Conv2d(32, 32, 5, padding=2, stride=1),
            nn.MaxPool2d(2, 2),
            nn.Conv2d(32, 64, 5, padding=2, stride=1),
            nn.MaxPool2d(2, 2),
            nn.Flatten(),
            nn.Linear(1024, 64),
            nn.Linear(64, 10)
        )

    def forward(self, x):
        x = self.model1(x)
        return x


loss = nn.CrossEntropyLoss()

tudui = Tudui()
for data in dataloader:
    imgs, targets = data
    outputs = tudui(imgs)
    result_loss = loss(outputs, targets)
    result_loss.backward()
