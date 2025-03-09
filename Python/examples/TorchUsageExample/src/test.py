import torch
import torchvision
from PIL import Image
from torchvision import transforms

test_data = torchvision.datasets.CIFAR10(root="../CIFAR10", train=False, download=True,
                                         transform=torchvision.transforms.ToTensor())

image_path = "../imgs/dog.jpg"
image = Image.open(image_path).convert('RGB')
print(image)

transform = transforms.Compose([transforms.Resize((32, 32)), transforms.ToTensor()])

image = transform(image)
print(image.shape) # type: ignore

model = torch.load("../models/tudui/tudui_188.pth")  # maximum Accuracy 0.7825
print(model)

image = torch.reshape(image, (1, 3, 32, 32)) # type: ignore
image = image.cuda()
model.eval()
with torch.no_grad():
    output = model(image)
print(output)

print(output.argmax(axis=1))
print(test_data.classes[output.argmax(axis=1).item()])
