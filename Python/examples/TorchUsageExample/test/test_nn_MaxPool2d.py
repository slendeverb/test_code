import torch
import torchvision
from torch.utils.data import DataLoader
from torch.utils.tensorboard.writer import SummaryWriter

# input = torch.tensor([[1, 2, 0, 3, 1],
#                       [0, 1, 2, 3, 1],
#                       [1, 2, 1, 0, 0],
#                       [5, 2, 3, 1, 1],
#                       [2, 1, 0, 1, 1]])
#
# input = torch.reshape(input, (-1, 1, 5, 5))

# print(input.shape)

dataset = torchvision.datasets.CIFAR10(root="../CIFAR10", train=False, transform=torchvision.transforms.ToTensor(),
                                       download=True)

dataloader = DataLoader(dataset, batch_size=64, shuffle=True)


class Tudui(torch.nn.Module):
    def __init__(self):
        super(Tudui, self).__init__()
        self.maxpool1 = torch.nn.MaxPool2d(kernel_size=3, stride=3, ceil_mode=True)

    def forward(self, input):
        output = self.maxpool1(input)
        return output


writer = SummaryWriter(log_dir="../logs")

step = 0
tudui = Tudui()
for data in dataloader:
    imgs, targets = data
    writer.add_image("input", imgs, step, dataformats="NCHW")
    outputs = tudui(imgs)
    writer.add_image("outputs", outputs, step, dataformats="NCHW")
    step += 1

writer.close()
