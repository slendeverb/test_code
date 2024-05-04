from trajGRU import *

forecaster_params = [
    [
        OrderedDict({'deconv1_leaky_1': [192, 192, 4, 2, 1]}),
        OrderedDict({'deconv2_leaky_1': [192, 64, 5, 3, 1]}),
        OrderedDict({
            'deconv3_leaky_1': [64, 8, 7, 5, 1],
            'conv3_leaky_2': [8, 8, 3, 1, 1],
            'conv3_3': [8, 1, 1, 1, 0]
        }),
    ],

    [
        TrajGRU(input_channel=192, num_filter=192,
                b_h_w=(batch_size, int(config['Sets']['ER31']), int(config['Sets']['ER32'])), zoneout=0.0, L=13,
                i2h_kernel=(3, 3), i2h_stride=(1, 1), i2h_pad=(1, 1),
                h2h_kernel=(3, 3), h2h_dilate=(1, 1),
                ),

        TrajGRU(input_channel=192, num_filter=192,
                b_h_w=(batch_size, int(config['Sets']['ER21']), int(config['Sets']['ER22'])), zoneout=0.0, L=13,
                i2h_kernel=(3, 3), i2h_stride=(1, 1), i2h_pad=(1, 1),
                h2h_kernel=(5, 5), h2h_dilate=(1, 1),
                ),
        TrajGRU(input_channel=64, num_filter=64,
                b_h_w=(batch_size, int(config['Sets']['ER11']), int(config['Sets']['ER12'])), zoneout=0.0, L=9,
                i2h_kernel=(3, 3), i2h_stride=(1, 1), i2h_pad=(1, 1),
                h2h_kernel=(5, 5), h2h_dilate=(1, 1),
                )
    ]
]


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
