#include "HarrFaceRecognition.h"

HarrFaceRecognition::HarrFaceRecognition(std::string filePath)
{
	img = cv::imread(filePath, cv::IMREAD_GRAYSCALE);	// �Ҷȷ�ʽ����ͼ��
	result = cv::imread(filePath);						// ��ɫ��ʽ����ͼ����Ϊ��ʾ���
	harr_xml = "haarcascade_frontalface_default.xml";
}

void HarrFaceRecognition::getFace()
{
	// ���������
	cv::CascadeClassifier object;
	// ���ز����ļ�
	object.load(harr_xml);
	// �������
	object.detectMultiScale(img, faces, 1.1, 3);
}

void HarrFaceRecognition::show()
{
	for (int i = 0; i < faces.size(); i++)
	{
		// �������ο����Ͻ�����
		int x = faces[i].tl().x;
		int y = faces[i].tl().y;
		// �������ο��Ⱥ͸߶�
		int width = faces[i].width;
		int height = faces[i].height;
		if (width * height > 100 * 100)
		{
			cv::rectangle(result, cv::Rect(x, y, width, height), cv::Scalar(255, 0, 0));
		}
	}
	cv::imshow("result", result);
	cv::waitKey(6000);
}