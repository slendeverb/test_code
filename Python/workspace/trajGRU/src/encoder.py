from trajGRU import *

encoder_params = [
    [
        OrderedDict({'conv1_leaky_1': [1, 8, 7, 5, 1]}),  # inputchannel,outputchannel,ks,stride,padding
        OrderedDict({'conv2_leaky_1': [64, 192, 5, 3, 1]}),
        OrderedDict({'conv3_leaky_1': [192, 192, 3, 2, 1]}),
    ],
    [
        TrajGRU(input_channel=8, num_filter=64,
                b_h_w=(batch_size, int(config['Sets']['ER11']), int(config['Sets']['ER12'])), zoneout=0.0, L=13,
                i2h_kernel=(3, 3), i2h_stride=(1, 1), i2h_pad=(1, 1),
                h2h_kernel=(5, 5), h2h_dilate=(1, 1)),

        TrajGRU(input_channel=192, num_filter=192,
                b_h_w=(batch_size, int(config['Sets']['ER21']), int(config['Sets']['ER22'])), zoneout=0.0, L=13,
                i2h_kernel=(3, 3), i2h_stride=(1, 1), i2h_pad=(1, 1),
                h2h_kernel=(5, 5), h2h_dilate=(1, 1)),

        TrajGRU(input_channel=192, num_filter=192,
                b_h_w=(batch_size, int(config['Sets']['ER31']), int(config['Sets']['ER32'])), zoneout=0.0, L=9,
                i2h_kernel=(3, 3), i2h_stride=(1, 1), i2h_pad=(1, 1),
                h2h_kernel=(3, 3), h2h_dilate=(1, 1))
    ]
]


class Encoder(nn.Module):
    def __init__(self, subnets, rnns):
        super().__init__()
        assert len(subnets) == len(rnns)

        self.blocks = len(subnets)
        for index, (params, rnn) in enumerate(zip(subnets, rnns), 1):
            setattr(self, 'stage' + str(index), make_layers(params))
            setattr(self, 'rnn' + str(index), rnn)

    def forward_by_stage(self, input, subnet, rnn):
        seq_number, batch_size, input_channel, height, width = input.size()
        input = torch.reshape(input, (-1, input_channel, height, width))
        input = subnet(input)
        input = torch.reshape(input, (seq_number, batch_size, input.size(1), input.size(2), input.size(3)))

        outputs_stage, state_stage = rnn(input, None)
        return outputs_stage, state_stage

    # input: 5D T*B*C*H*W

    def forward(self, input):
        hidden_states = []
        logging.debug(input.size())

        for i in range(1, self.blocks + 1):
            input, state_stage = self.forward_by_stage(input, getattr(self, 'stage' + str(i)),
                                                       getattr(self, 'rnn' + str(i)))
            # print(f'stage {i} shape {input.shape} rnn {i} shape {state_stage.shape}')
            hidden_states.append(state_stage)

        return tuple(hidden_states)
