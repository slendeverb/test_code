import os
import zlib
import numpy as np
from PIL import Image

# 设置文件夹路径
folder_path = "C:/Users/slendeverb/Documents/Tencent Files/571641990/FileRecv/学校事务/大创/HKO-7/radarPNG_mask"

for root, dirs, files in os.walk(folder_path):
    # 遍历文件夹中的所有文件
    for filename in files:
        # 检查文件扩展名是否为 ".mask"
        if filename.endswith(".mask"):
            # 构建文件路径
            mask_path = os.path.join(root, filename)
            # 构建输出文件路径，将 ".mask" 替换为 ".png"
            png_path = os.path.join(root.replace("radarPNG_mask", "radarPNG_mask_png"),
                                    filename.replace(".mask", ".png"))
            # 创建目标文件夹
            os.makedirs(os.path.dirname(png_path), exist_ok=True)

            # 读取掩码文件
            with open(mask_path, 'rb') as f:
                dat = zlib.decompress(f.read())
                mask = np.frombuffer(dat, dtype=bool).reshape((480, 480))

            # 将矩阵转换为图像
            img = Image.fromarray(255 - mask.astype(np.uint8) * 255)

            # 保存图像为 PNG 格式
            img.save(png_path)

print("转换完成！")
