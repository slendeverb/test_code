import os
import cv2
import numpy as np
from torch.utils.data import Dataset


class MyData(Dataset):
    def __init__(self, root_dir, label_dir):
        self.root_dir = root_dir
        self.label_dir = label_dir
        self.path = os.path.join(self.root_dir, self.label_dir)
        self.img_path = os.listdir(str(self.path))

    def __getitem__(self, idx):
        img_name = self.img_path[idx]
        img_item_path = os.path.join(self.root_dir, self.label_dir, img_name)
        img = cv2.imdecode(np.fromfile(img_item_path, dtype=np.uint8), cv2.IMREAD_COLOR)
        label = self.label_dir
        return img, label

    def __len__(self):
        return len(self.img_path)


root_dir = "../hymenoptera_data/train"
ants_label_dir = "ants"
bees_label_dir = "bees"
ants_dataset = MyData(root_dir, ants_label_dir)
bees_dataset = MyData(root_dir, bees_label_dir)
train_dataset = ants_dataset + bees_dataset
