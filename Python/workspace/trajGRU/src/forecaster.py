import torch
from torch import nn
from torchvision.models.vgg import make_layers


class Forecaster(nn.Module):
    def __init__(self, subnets, rnns):
        super().__init__()
        assert len(subnets) == len(rnns)

        self.blocks = len(subnets)
        # use transposeConv to enlarge outputs
        # self.transposeConv2d = nn.ConvTranspose2d(in_channels=1, out_channels=1,kernel_size=5, stride=(5,7),padding=1)
        for index, (params, rnn) in enumerate(zip(subnets, rnns)):
            setattr(self, 'rnn' + str(self.blocks - index), rnn)
            setattr(self, 'stage' + str(self.blocks - index), make_layers(params))

        # self.conv2d = nn.Conv2d(in_channels=1,out_channels=1,kernel_size=3,padding=1)

    def forward_by_stage(self, input, state, subnet, rnn):
        input, state_stage = rnn(input, state, seq_len=20)  # 20 frames要注意这里的序列长度设置
        seq_number, batch_size, input_channel, height, width = input.size()
        input = torch.reshape(input, (-1, input_channel, height, width))
        input = subnet(input)
        input = torch.reshape(input, (seq_number, batch_size, input.size(1), input.size(2), input.size(3)))
        return input

    def forward_transpose(self, input):
        seq_number, batch_size, input_channel, height, width = input.size()
        input = torch.reshape(input, (-1, input_channel, height, width))
        # enlarge outputs
        # use conv to delete noise
        # input = self.transposeConv2d(input)
        # input = self.conv2d(input)
        input = torch.reshape(input, (seq_number, batch_size, input.size(1), input.size(2), input.size(3)))
        return input

    def forward(self, hidden_states):
        input = self.forward_by_stage(None, hidden_states[-1], getattr(self, 'stage3'), getattr(self, 'rnn3'))

        for i in list(range(1, self.blocks))[::-1]:
            input = self.forward_by_stage(input, hidden_states[i - 1], getattr(self, 'stage' + str(i)),
                                          getattr(self, 'rnn' + str(i)))
            # print(f'forcaster {i} shape {input.shape}')

        input = self.forward_transpose(input)
        # print(input.shape)
        return input
