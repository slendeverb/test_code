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
print(image.shape)

model = torch.load("../models/vgg16/not_trained/vgg16_false.pth")
print(model)

image = torch.reshape(image, (1, 3, 32, 32))
image = image.cuda()
model.eval()
with torch.no_grad():
    output = model(image)
print(output)

print(output.argmax(axis=1))
print(test_data.classes[output.argmax(axis=1).item()])
