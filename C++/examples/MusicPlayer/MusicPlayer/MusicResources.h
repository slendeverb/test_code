#pragma once
#include"common.h"

class MusicResources
{
public:
	static MusicResources& getMusicResources();		// �ṩ���нӿڷ�������
	static bool isMusic(const std::string& name);	// ��ǰ�ļ��Ƿ��������ļ�(.mp3)
	static std::string getNewName(std::string name);// ��ԭ�����ļ������еĿո��滻Ϊ_
	static void traverseAllFile();					// �����û��ṩ���ļ���������.mp3�ļ�
	static std::map<int, std::string> music;
private:
	MusicResources();	//����ģʽ�Ĺؼ���
};