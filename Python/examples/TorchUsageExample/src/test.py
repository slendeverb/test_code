import torch
from PIL import Image
from torchvision import transforms

image_path = "../imgs/dog.jpg"
image = Image.open(image_path).convert('RGB')
print(image)

transform = transforms.Compose([transforms.Resize((32, 32)), transforms.ToTensor()])

image = transform(image)
print(image.shape)

model = torch.load("../models/tudui/tudui_20.pth")
print(model)

image = torch.reshape(image, (1, 3, 32, 32))
image = image.cuda()
model.eval()
with torch.no_grad():
    output = model(image)
print(output)

print(output.argmax(axis=1))
