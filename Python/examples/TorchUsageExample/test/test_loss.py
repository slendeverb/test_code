import torch
import torch.nn as nn

inputs = torch.tensor([1, 2, 3], dtype=torch.float32)
targets = torch.tensor([1, 2, 5])

# inputs = torch.reshape(inputs, (1, 1, 1, 3))
# targets = torch.reshape(targets, (1, 1, 1, 3))

loss_L1 = nn.L1Loss(reduction='sum')
outputs = loss_L1(inputs, targets)
print(outputs)

loss_MSE = nn.MSELoss()
outputs = loss_MSE(inputs, targets)
print(outputs)

x = torch.tensor([0.1, 0.2, 0.3])
y = torch.tensor([1])
x = torch.reshape(x, (1, -1))
loss_CorssEntropy = nn.CrossEntropyLoss()
outputs = loss_CorssEntropy(x, y)
print(outputs)
