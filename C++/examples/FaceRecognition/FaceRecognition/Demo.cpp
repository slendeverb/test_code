#include"HarrFaceRecognition.h"

int main()
{
	std::locale loc{ ".utf8" };
	std::locale::global(loc);
	HarrFaceRecognition harrFaceRecognition("./picture/美女图片.jpg");
	harrFaceRecognition.getFace();
	harrFaceRecognition.show();
	return 0;
}