import torch
import torchvision
from torch import nn

vgg16_false = torchvision.models.vgg16(weights=None)
vgg16_true = torchvision.models.vgg16(weights="DEFAULT")
# 保存方式1 模型结构+模型参数
torch.save(vgg16_false, "../models/vgg16/not_trained/vgg16_method1.pth")
torch.save(vgg16_true,"../models/vgg16/trained/vgg16_method1.pth")

# 保存方式2 模型参数(官方推荐)
# torch.save(vgg16_false.state_dict(), "../models/vgg16/not_trained/vgg16_method2.pth")

# 陷阱 (新版本不用import)
# class Tudui(torch.nn.Module):
#     def __init__(self):
#         super(Tudui, self).__init__()
#         self.conv1=nn.Conv2d(3,64,3)
#
#     def forward(self,x):
#         x=self.conv1(x)
#         return x
#
# tudui=Tudui()
# torch.save(tudui,"../models/myModel/not_trained/tudui_method1.pth")