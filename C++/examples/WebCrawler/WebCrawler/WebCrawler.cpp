#include "WebCrawler.h"

WebCrawler::WebCrawler(std::string url)
{
	this->url = url;
}

void WebCrawler::parseUrl()
{
	if (url.empty())
	{
		std::cout << "the url is empty!" << std::endl;
		return;
	}
	
	size_t hostStart = url.find("//");
	hostStart = (hostStart == url.npos) ? 0 : hostStart + sizeof("//")-1;

	size_t resStart = url.find("/", hostStart);
	if (resStart == url.npos)
	{
		host = url.substr(hostStart);
		resPath = "/";
	}
	else
	{
		host = url.substr(hostStart, resStart - hostStart);
		resPath = url.substr(resStart);
	}

	std::cout <<"host:"<< host << std::endl;
	std::cout << "resource:" << resPath << std::endl;
}

void WebCrawler::crawlerConnect()
{
	// 打开socket 2.2
	WSADATA wsaData;
	WSAStartup(MAKEWORD(2, 2), &wsaData);

	// 创建socket
	fd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (fd == SOCKET_ERROR)
	{
		std::cout << "create socket failed " << WSAGetLastError() << std::endl;
		return;
	}

	// 通过域名获取IP地址
	HOSTENT *hent = gethostbyname(host.c_str());
	if (!hent)
	{
		std::cout << "get host ip failed " << WSAGetLastError() << std::endl;
		return;
	}

	// 连接服务器
	SOCKADDR_IN addr;
	addr.sin_family = AF_INET;
	addr.sin_port = htons(80);
	// addr.sin_addr.S_un.S_addr = inet_addr("127.0.0.1");
	memcpy(&addr.sin_addr, hent->h_addr, sizeof(IN_ADDR));
	if (SOCKET_ERROR == connect(fd, (sockaddr*)&addr, sizeof(addr)))
	{
		std::cout << "connect failed " << WSAGetLastError() << std::endl;
		return;
	}
}

void WebCrawler::callServer()
{
	// 给服务器发送请求
	std::string header;
	header += "GET " + resPath + " HTTP/1.1\r\n";
	header += "Host:" + host + "\r\n";
	header += "Connection:Close\r\n";
	header += "\r\n";

	if (SOCKET_ERROR == send(fd, header.c_str(), (int)header.size(), 0))
	{
		std::cout << "send failed " << WSAGetLastError() << std::endl;
		return;
	}
}

std::string WebCrawler::getImgUrl(const std::string& html)
{
	std::string imgUrl;
	size_t imgBegin = html.find("img src=\"");
	if (imgBegin == html.npos)
	{
		std::cout << "not find resources" << std::endl;
		return "";
	}

	imgBegin += sizeof("img src=\"")-1;
	size_t imgEnd = html.find("\"", imgBegin);
	if (imgEnd == html.npos)
	{
		std::cout << "web page error" << std::endl;
		return "";
	}

	imgUrl = html.substr(imgBegin, imgEnd - imgBegin);
	return imgUrl;
}

void WebCrawler::getHtml()
{
	// 连接服务器
	crawlerConnect();

	callServer();

	std::string html;
	html.resize(1024 * 1024);
	// 获取网页
	int len = recv(fd, html.data(), (int)html.size(), 0);
	if (len == SOCKET_ERROR)
	{
		std::cout << "receive failed " << WSAGetLastError() << std::endl;
		return;
	}

	// 保存到文件
	std::ofstream out;
	out.open("web.html");
	if (out.is_open())
	{
		out << html << std::endl;
		out.close();
	}
}
