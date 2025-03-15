#include "MusicResources.h"

std::wstring UTF8ToUnicode(const std::string& str) {
	std::wstring ret;
	try {
		std::wstring_convert<std::codecvt_utf8<wchar_t> > wcv;
		ret = wcv.from_bytes(str);
	}
	catch (const std::exception& e) {
		std::cerr << e.what() << std::endl;
	}
	return ret;
}

std::map<int, std::string> MusicResources::music;

MusicResources& MusicResources::getMusicResources()
{
	static MusicResources instance;
	return instance;
}

bool MusicResources::isMusic(const std::string& name)
{
	// 怎么判断一个字符串的结尾是.mp3
	int length = name.size();
	// substr 截取函数
	return name.substr(length - 4) == ".mp3";
}

// 把字符串中的空格替换为_
std::string MusicResources::getNewName(std::string name)
{
	for (int i = 0; i < name.size(); i++)
	{
		if (name[i] == ' ')
		{
			name[i] = '_';
		}
	}
	return name;
}

void MusicResources::traverseAllFile()
{
	std::cout << "请输入音乐库URL:";
	std::string urlRoot;
	std::cin >> urlRoot;
	// 判定路径是否存在
	std::filesystem::path url(urlRoot);
	if (!std::filesystem::exists(url))
	{
		std::cout << "URL error..............." << std::endl;
		exit(0);
	}
	// 得到当前路径下所有音乐的文件路径
	// C++新标准中路径管理filesystem
	int pos = 0;
	std::string oldName;
	std::string newName;
	std::filesystem::directory_iterator begin(url);
	for (std::filesystem::directory_iterator end; begin != end; ++begin)
	{
		// 当它不是文件夹的时候
		if (!std::filesystem::is_directory(begin->path()))
		{
			// 判断是不是.mp3文件
			if (isMusic(begin->path().filename().string()))
			{
				// 把所有音乐文件名中的空格替换为_
				oldName = urlRoot + "/" + begin->path().filename().string();
				newName = getNewName(oldName);
				int result = rename(oldName.c_str(), newName.c_str());
				music[++pos] = newName;
			}
		}
	}
	if (pos == 0)
	{
		std::cout << "音乐库中没有音乐.........." << std::endl;
		exit(0);
	}
	else
	{
		std::cout << "音乐库加载成功..............." << std::endl;
	}
}

MusicResources::MusicResources()
{
	std::cout << "初始化音乐库.............." << std::endl;
	traverseAllFile();
}
