import torch
from torch import nn
from torch.utils.tensorboard.writer import SummaryWriter


class Tudui(nn.Module):
    def __init__(self):
        super(Tudui, self).__init__()
        # self.conv1 = nn.Conv2d(3, 32, 5, padding=2, stride=1)
        # self.maxpool1 = nn.MaxPool2d(2, 2)
        # self.conv2 = nn.Conv2d(32, 32, 5, padding=2, stride=1)
        # self.maxpool2 = nn.MaxPool2d(2, 2)
        # self.conv3 = nn.Conv2d(32, 64, 5, padding=2, stride=1)
        # self.maxpool3 = nn.MaxPool2d(2, 2)
        # self.flatten = nn.Flatten()
        # self.linear1 = nn.Linear(1024, 64)
        # self.linear2 = nn.Linear(64, 10)

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


tudui = Tudui()
print(tudui)
input = torch.ones((64, 3, 32, 32))
output = tudui(input)
print(output.shape)

writer = SummaryWriter(log_dir='../logs')

writer.add_graph(tudui, input)
writer.close()
