import os

import numpy as np
import torch
from nowcasting.config import cfg
from nowcasting.models.forecaster import Forecaster
from nowcasting.models.encoder import Encoder
from nowcasting.models.model import EF
from experiments.net_params import encoder_params, forecaster_params

encoder = Encoder(encoder_params[0], encoder_params[1]).to(cfg.GLOBAL.DEVICE)
forecaster = Forecaster(forecaster_params[0], forecaster_params[1]).to(cfg.GLOBAL.DEVICE)
encoder_forecaster = EF(encoder, forecaster).to(cfg.GLOBAL.DEVICE)
encoder_forecaster.load_state_dict(torch.load('./trained_models/encoder_forecaster_45000.pth'))

print(encoder_forecaster)
