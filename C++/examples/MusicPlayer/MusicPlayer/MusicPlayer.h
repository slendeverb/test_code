#pragma once
#include"common.h"

class MusicPlayer
{
public:
	MusicPlayer();
	void menu();		// 界面
	void keyDown();		// 按键交互
	void showMusic();	// 显示歌单
	void playMusic();	// 播放音乐
	void pauseMusic();	// 暂停音乐
	void resumeMusic();	// 继续音乐
	void closeMusic();	// 关闭音乐
	void nextMusic();	// 下一首
	void prevMusic();	// 上一首
	void randomPlay();	// 随机播放
private:
	int curIndex;	// 当前音乐的下标
	int totalNum;	// 音乐总数
	std::vector<std::string> cmd;	// 播放命令，转存为字符串，便于做音乐播放，指令链接

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