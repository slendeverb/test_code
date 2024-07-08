#pragma once
#include"common.h"

class MusicResources
{
public:
	static MusicResources& getMusicResources();		// 提供共有接口访问数据
	static bool isMusic(const std::string& name);	// 当前文件是否是音乐文件(.mp3)
	static std::string getNewName(std::string name);// 把原音乐文件名称中的空格替换为_
	static void traverseAllFile();					// 遍历用户提供的文件夹下所有.mp3文件
	static std::map<int, std::string> music;
private:
	MusicResources();	//单例模式的关键点
};

std::wstring UTF8ToUnicode(const std::string& str);