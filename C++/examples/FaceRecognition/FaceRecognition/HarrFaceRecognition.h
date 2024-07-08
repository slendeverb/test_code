#pragma once
#include"common.h"

class HarrFaceRecognition
{
public:
	HarrFaceRecognition(std::string url);
	void getFace();
	void show();
private:
	cv::Mat img;					// 原图--->灰度加载
	cv::Mat result;					// 显示
	std::string harr_xml;			// 采样文件路径
	std::vector<cv::Rect> faces;	// 人脸		cv::Rect-->矩形 左上角坐标，宽度，高度
};