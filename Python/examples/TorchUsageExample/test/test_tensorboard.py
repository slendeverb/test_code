from torch.utils.tensorboard.writer import SummaryWriter
import cv2
import numpy as np

writer = SummaryWriter("../logs")

for i in range(100):
    writer.add_scalar("y=2x", 2 * i, i)

image_path = "../hymenoptera_data/train/ants/0013035.jpg"
image = cv2.imdecode(np.fromfile(image_path, dtype=np.uint8), cv2.IMREAD_COLOR)
writer.add_image("ant", image, 1, dataformats="HWC")
writer.close()
