#pragma once
#include"common.h"

class WebCrawler
{
public:
	WebCrawler(std::string);						// ��ʼ��url
	void parseUrl();								// ����url ��ȡ����������Դ·��
	void crawlerConnect();							// ���ӷ�����
	void callServer();								// ���������������
	std::string getImgUrl(const std::string& html);	// ��ȡ��ҳ���ͼƬ����
	void getHtml();									// ��ȡ��ҳ
private:
	std::string url;
	std::string host;
	std::string resPath;
	SOCKET fd;
};