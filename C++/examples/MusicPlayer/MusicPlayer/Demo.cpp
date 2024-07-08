/*
	1.音乐文件管理-->歌单
		1.1 资源管理-->单例模式实现资源管理
	2.播放器制作
		2.1 菜单
		2.2 按键处理
		2.3 实现功能
	3.音乐播放：mciSendString("order musicURL",0,0,0)函数
				指令：open 打开音乐文件
					play 播放音乐文件
					pause 暂停
					resume 继续
	4.逻辑处理
		4.1 上一首和下一首边界处理
		4.4 随机播放
*/
#include"MusicPlayer.h"
#include"MusicResources.h"

int main()
{
	std::locale loc{ ".utf8" };
	std::locale::global(loc);
	std::cout.imbue(loc);
	MusicPlayer musicPlayer;
	while (true)
	{
		musicPlayer.menu();
		musicPlayer.keyDown();
		system("cls");
	}
	return 0;
}