import torch
import torchvision
from torch import nn

# 读取方式1 加载模型
model = torch.load("../models/vgg16/not_trained/vgg16_method1.pth")

# 读取方式2 加载字典
# model = torchvision.models.vgg16(weights=None)
# model.load_state_dict(torch.load("../models/vgg16/not_trained/vgg16_method2.pth"))

# 陷阱
# from test_model_save import *
# model = torch.load("../models/tudui/not_trained/tudui_method1.pth")

print(model)
