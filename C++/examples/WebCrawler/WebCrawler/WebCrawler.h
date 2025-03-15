#pragma once
#include"common.h"

class WebCrawler
{
public:
	WebCrawler(std::string);						// 初始化url
	void parseUrl();								// 解析url 获取主机名和资源路径
	void crawlerConnect();							// 连接服务器
	void callServer();								// 向服务器发送请求
	std::string getImgUrl(const std::string& html);	// 获取网页里的图片链接
	void getHtml();									// 获取网页
private:
	std::string url;
	std::string host;
	std::string resPath;
	SOCKET fd;
};