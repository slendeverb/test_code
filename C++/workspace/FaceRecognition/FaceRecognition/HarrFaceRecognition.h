#pragma once
#include"common.h"

class HarrFaceRecognition
{
public:
	HarrFaceRecognition(std::string url);
	void getFace();
	void show();
private:
	cv::Mat img;					// ԭͼ--->�Ҷȼ���
	cv::Mat result;					// ��ʾ
	std::string harr_xml;			// �����ļ�·��
	std::vector<cv::Rect> faces;	// ����		cv::Rect-->���� ���Ͻ����꣬���ȣ��߶�
};