#pragma once
#include"common.h"

class MusicPlayer
{
public:
	MusicPlayer();
	void menu();		// ����
	void keyDown();		// ��������
	void showMusic();	// ��ʾ�赥
	void playMusic();	// ��������
	void pauseMusic();	// ��ͣ����
	void resumeMusic();	// ��������
	void closeMusic();	// �ر�����
	void nextMusic();	// ��һ��
	void prevMusic();	// ��һ��
	void randomPlay();	// �������
private:
	int curIndex;	// ��ǰ���ֵ��±�
	int totalNum;	// ��������
	std::vector<std::string> cmd;	// �������ת��Ϊ�ַ��������������ֲ��ţ�ָ������

	enum class Operation
	{
		EXIT,
		PLAY,
		PAUSE,
		RESUME,
		NEXT_ONE,
		PREV_ONE,
		RANDOM_PLAY
	};

	enum class CMD
	{
		OPEN,
		PLAY,
		PAUSE,
		RESUME,
		CLOSE
	};
};