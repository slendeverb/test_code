#include "HarrFaceRecognition.h"

HarrFaceRecognition::HarrFaceRecognition(std::string filePath)
{
	img = cv::imread(filePath, cv::IMREAD_GRAYSCALE);	// 灰度方式加载图像
	result = cv::imread(filePath);						// 彩色方式加载图像作为显示结果
	harr_xml = "haarcascade_frontalface_default.xml";
}

void HarrFaceRecognition::getFace()
{
	// 创建检测器
	cv::CascadeClassifier object;
	// 加载采样文件
	object.load(harr_xml);
	// 检测人脸
	object.detectMultiScale(img, faces, 1.1, 3);
}

void HarrFaceRecognition::show()
{
	for (int i = 0; i < faces.size(); i++)
	{
		// 人脸矩形框左上角坐标
		int x = faces[i].tl().x;
		int y = faces[i].tl().y;
		// 人脸矩形宽度和高度
		int width = faces[i].width;
		int height = faces[i].height;
		if (width * height > 100 * 100)
		{
			cv::rectangle(result, cv::Rect(x, y, width, height), cv::Scalar(255, 0, 0));
		}
	}
	cv::imshow("result", result);
	cv::waitKey(0);
}
