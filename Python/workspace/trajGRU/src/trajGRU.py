import torch
import torch.nn as nn
import torch.nn.functional as F


class BaseConvRNN(nn.Module):
    def __init__(
        self,
        num_filter,
        b_h_w,
        h2h_kernel=(3, 3),
        h2h_dilate=(1, 1),
        i2h_kernel=(3, 3),
        i2h_stride=(1, 1),
        i2h_pad=(1, 1),
        i2h_dilate=(1, 1),
        act_type=torch.tanh,
        prefix="BaseConvRNN",
    ):
        super(BaseConvRNN, self).__init__()
        self._prefix = prefix
        self._num_filter = num_filter
        self._h2h_kernel = h2h_kernel
        assert (self._h2h_kernel[0] % 2 == 1) and (self._h2h_kernel[1] % 2 == 1), print(
            "Only support odd number, get h2h_kernel= %s" % str(h2h_kernel)
        )

        self._h2h_pad = (
            h2h_dilate[0] * (h2h_kernel[0] - 1) // 2,
            h2h_dilate[1] * (h2h_kernel[1] - 1) // 2,
        )

        self._h2h_dilate = h2h_dilate
        self._i2h_kernel = i2h_kernel
        self._i2h_stride = i2h_stride
        self._i2h_pad = i2h_pad
        self._i2h_dilate = i2h_dilate
        self._act_type = act_type

        assert len(b_h_w) == 3
        i2h_dilate_ksize_h = 1 + (self._i2h_kernel[0] - 1) * self._i2h_dilate[0]
        i2h_dilate_ksize_w = 1 + (self._i2h_kernel[1] - 1) * self._i2h_dilate[1]
        self._batch_size, self._height, self._width = b_h_w
        self._state_height = (
            self._height + 2 * self._i2h_pad[0] - i2h_dilate_ksize_h
        ) // self._i2h_stride[0] + 1
        self._state_width = (
            self._width + 2 * self._i2h_pad[1] - i2h_dilate_ksize_w
        ) // self._i2h_stride[1] + 1
        self._curr_states = None
        self._counter = 0


def wrap(input, flow):
    B, C, H, W = input.size()
    # mesh grid
    xx = torch.arange(0, W).view(1, -1).repeat(H, 1).cuda()
    yy = torch.arange(0, H).view(-1, 1).repeat(1, W).cuda()

    xx = xx.view(1, 1, H, W).repeat(B, 1, 1, 1)
    yy = yy.view(1, 1, H, W).repeat(B, 1, 1, 1)

    grid = torch.cat((xx, yy), 1).float()
    vgrid = grid + flow

    vgrid[:, 0, :, :] = torch.sub(
        torch.div(torch.mul(2, vgrid[:, 0, :, :].clone().detach()), torch.sub(W, 1)),
        1.0,
    )
    vgrid[:, 1, :, :] = torch.sub(
        torch.div(torch.mul(2, vgrid[:, 1, :, :].clone().detach()), torch.sub(H, 1)),
        1.0,
    )
    # scale grid to [-1,1]
    # vgrid[:,0,:,:] = 2.0 * vgrid[:,0,:,:].clone() / max(W-1,1) - 1.0
    # vgrid[:,1,:,:] = 2.0 * vgrid[:,1,:,:].clone() / max(H-1,1) - 1.0
    # N C H W
    vgrid = vgrid.permute(0, 2, 3, 1)  # N*H*W*C
    output = F.grid_sample(input, vgrid)
    # output = bilinear_interpolate_torch_2D(input,vgrid)
    # print(output.shape)
    return output


class TrajGRU(BaseConvRNN):
    def __init__(
        self,
        input_channel,
        num_filter,
        b_h_w,
        zoneout=0.2,
        L=5,
        i2h_kernel=(3, 3),
        i2h_stride=(1, 1),
        i2h_pad=(1, 1),
        h2h_kernel=(5, 5),
        h2h_dilate=(1, 1),
        prefix="BaseConvRNN",
    ):
        super(TrajGRU, self).__init__(
            num_filter=num_filter,
            b_h_w=b_h_w,
            h2h_kernel=h2h_kernel,
            h2h_dilate=h2h_dilate,
            i2h_kernel=i2h_kernel,
            i2h_stride=i2h_stride,
            i2h_pad=i2h_pad,
            prefix=prefix,
        )
        self._L = L
        self._zoneout = zoneout

        # self._act_type = F.leaky_relu()
        # *3 according to 3 hidden states.
        self.i2h = nn.Conv2d(
            in_channels=input_channel,
            out_channels=self._num_filter * 3,
            kernel_size=self._i2h_kernal,
            stride=self._i2h_stride,
            padding=self._i2h_padding,
            dilation=self._i2h_dilate,
        )

        # inputs to flow
        self.i2f_conv1 = nn.Conv2d(
            in_channels=input_channel,
            out_channels=32,
            kernel_size=(5, 5),
            stride=(1, 1),
            padding=(2, 2),
            dilation=(1, 1),
        )

        # hidden to flow
        self.h2f_conv1 = nn.Conv2d(
            in_channels=self._num_filter,
            out_channels=32,
            kernel_size=(5, 5),
            stride=(1, 1),
            padding=(2, 2),
            dilation=(1, 1),
        )

        # generate flow
        self.flows_conv = nn.Conv2d(
            in_channels=32,
            out_channels=self._L * 2,
            kernel_size=(5, 5),
            stride=(1, 1),
            padding=(2, 2),
        )

        # hh, hz, hr, 1*1 ks
        self.ret = nn.Conv2d(
            in_channels=self._num_filter * self._L,
            out_channels=self._num_filter * 3,
            kernel_size=(1, 1),
            stride=(1, 1),
        )

        # inputs: B C H W
        # flow comes from current inputs and forward states
        def _flow_generator(self, inputs, states):
            if inputs is not None:
                i2f_conv1 = self.i2f_conv1(inputs)
            else:
                i2f_conv1 = None
            h2f_conv1 = self.h2f_conv1(states)
            f_conv1 = i2f_conv1 + h2f_conv1 if i2f_conv1 is not None else h2f_conv1
            f_conv1 = F.leaky_relu(f_conv1, 0.2, inplace=True)

            flows = self.flows_conv(f_conv1)
            # channels L*2, split according to 2
            # get L flow maps each have 2 channels
            flows = torch.split(flows, 2, dim=1)
            return flows

        # inputs states
        # inputs: S B C H W
        def forward(self, inputs=None, states=None, seq_len=5):
            global next_h
            if states is None:
                states = torch.zeros(
                    (
                        inputs.size(1), # type: ignore
                        self._num_filter,
                        self._state_height,
                        self._state_width,
                    ),
                    dtype=torch.float,
                ).cuda()

            if inputs is not None:
                S, B, C, H, W = inputs.size()
                i2h = self.i2h(torch.reshape(inputs, (-1, C, H, W)))
                i2h = torch.reshape(i2h, (S, B.i2h.size(1), i2h.size(2), i2h.size(3)))
                i2h_slice = torch.split(i2h, self._num_filter, dim=2)
            else:
                i2h_slice = None

            prev_h = states
            outputs = []
            for i in range(seq_len):
                if inputs is not None:
                    flows = self._flow_generator(inputs[i, ...], prev_h)
                else:
                    flows = self._flow_generator(None, prev_h)

                wrapped_data = []

                for j in range(len(flows)):
                    flow = flows[j]
                    wrapped_data.append(wrap(prev_h, -flow))
                wrapped_data = torch.cat(wrapped_data, dim=1)
                h2h = self.ret(wrapped_data)
                h2h_slice = torch.split(h2h, self._num_filter, dim=1)
                if i2h_slice is not None:
                    reset_gate = torch.sigmoid(i2h_slice[0][i, ...] + h2h_slice[0])
                    update_gate = torch.sigmoid(i2h_slice[1][i, ...] + h2h_slice[1])
                    new_mem = F.leaky_relu(
                        i2h_slice[2][i, ...] + reset_gate * h2h_slice[2],
                        0.2,
                        inplace=True,
                    )
                else:
                    reset_gate = torch.sigmoid(h2h_slice[0])
                    update_gate = torch.sigmoid(h2h_slice[1])
                    new_mem = F.leaky_relu(reset_gate * h2h_slice[2], 0.2, inplace=True)

                next_h = update_gate * prev_h + (1 - update_gate) * new_mem

                if self._zoneout > 0.0:
                    mask = F.dropout2d(torch.zeros_like(prev_h), p=self._zoneout)
                    next_h = torch.where(mask, next_h, prev_h)
                outputs.append(next_h)
                prev_h = next_h
            return torch.stack(outputs), next_h
