#include"WebCrawler.h"

int main()
{
	WebCrawler webCrawler("http://www.netbian.com/desk/32399-1920x1080.htm");
	webCrawler.parseUrl();
	webCrawler.getHtml();
	return 0;
}