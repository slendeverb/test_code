import torch
import torchvision
from torch.utils.data import DataLoader
import torch.nn as nn
from torch.utils.tensorboard.writer import SummaryWriter

dataset = torchvision.datasets.CIFAR10(
    "../CIFAR10", train=False, transform=torchvision.transforms.ToTensor(), download=True
)

dataloader = DataLoader(dataset, batch_size=64, shuffle=True, num_workers=0,drop_last=False)


class Tudui(nn.Module):
    def __init__(self) -> None:
        super().__init__()
        self.conv1 = nn.Conv2d(
            in_channels=3, out_channels=6, kernel_size=3, stride=1, padding=0
        )

    def forward(self, x):
        x = self.conv1(x)
        return x


tudui = Tudui()
# print(tudui)

writer = SummaryWriter(log_dir="../logs")

step = 0
for data in dataloader:
    imgs, targets = data
    output = tudui(imgs)
    # print(imgs.shape)
    # print(output.shape)

    writer.add_image("input", imgs, step, dataformats="NCHW")
    output = torch.reshape(output, (-1, 3, 30, 30))
    writer.add_image("output", output, step, dataformats="NCHW")

    step += 1

writer.close()
