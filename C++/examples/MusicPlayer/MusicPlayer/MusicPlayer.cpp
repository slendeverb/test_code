#include "MusicPlayer.h"
#include"MusicResources.h"

MusicPlayer::MusicPlayer()
{
	MusicResources::getMusicResources();
	curIndex = 1;
	totalNum = static_cast<decltype(totalNum)>(MusicResources::music.size());
	cmd.emplace_back("open ");
	cmd.emplace_back("play ");
	cmd.emplace_back("pause ");
	cmd.emplace_back("resume ");
	cmd.emplace_back("close ");
}

void MusicPlayer::menu()
{
	using std::cout;
	using std::endl;

	cout << "--------------------【音乐播放器】---------------------" << endl;
	cout << "\t\t0.退出" << endl;
	cout << "\t\t1.播放音乐" << endl;
	cout << "\t\t2.暂停" << endl;
	cout << "\t\t3.继续" << endl;
	cout << "\t\t4.下一首" << endl;
	cout << "\t\t5.上一首" << endl;
	cout << "\t\t6.随机播放" << endl;
	cout << "--------------------------------------------------------" << endl;
}

void MusicPlayer::keyDown()
{
	int userKey = 0;
	// 显示歌单-->资源管理
	showMusic();
	std::cout << "请输入你的选择:";
	std::cin >> userKey;
	switch (static_cast<Operation>(userKey))
	{
		using enum Operation;
	case EXIT:
		closeMusic();
		exit(0);
		break;
	case PLAY:
		playMusic();
		break;
	case PAUSE:
		pauseMusic();
		break;
	case RESUME:
		resumeMusic();
		break;
	case NEXT_ONE:
		nextMusic();
		break;
	case PREV_ONE:
		prevMusic();
		break;
	case RANDOM_PLAY:
		randomPlay();
		break;
	default:
		break;
	}
}

void MusicPlayer::showMusic()
{
	std::cout << "歌单:" << std::endl;
	for (auto v : MusicResources::music)
	{
		if (v.first == curIndex)
		{
			std::cout << v.first << '\t' << v.second << "\t\t\t" << "<----" << std::endl;
		}
		else
		{
			std::cout << v.first << '\t' << v.second << std::endl;
		}
	}
}

void MusicPlayer::playMusic()
{
	std::string openCmd = cmd[static_cast<int>(CMD::OPEN)] + MusicResources::music[curIndex];
	mciSendString(UTF8ToUnicode(openCmd).c_str(), NULL, 0, NULL);
	std::string playCmd = cmd[static_cast<int>(CMD::PLAY)] + MusicResources::music[curIndex];
	mciSendString(UTF8ToUnicode(playCmd).c_str(), NULL, 0, NULL);
}

void MusicPlayer::pauseMusic()
{
	std::string pauseCmd = cmd[static_cast<int>(CMD::PAUSE)] + MusicResources::music[curIndex];
	mciSendString(UTF8ToUnicode(pauseCmd).c_str(), NULL, 0, NULL);
}

void MusicPlayer::resumeMusic()
{
	std::string resumeCmd = cmd[static_cast<int>(CMD::RESUME)] + MusicResources::music[curIndex];
	mciSendString(UTF8ToUnicode(resumeCmd).c_str(), NULL, 0, NULL);
}

void MusicPlayer::closeMusic()
{
	std::string closeCmd = cmd[static_cast<int>(CMD::CLOSE)] + MusicResources::music[curIndex];
	mciSendString(UTF8ToUnicode(closeCmd).c_str(), NULL, 0, NULL);
}

void MusicPlayer::nextMusic()
{
	closeMusic();
	curIndex == MusicResources::music.size() ? curIndex = 1 : ++curIndex;
	playMusic();
}

void MusicPlayer::prevMusic()
{
	closeMusic();
	curIndex == 1 ? curIndex = static_cast<int>(MusicResources::music.size()) : --curIndex;
	playMusic();
}

void MusicPlayer::randomPlay()
{
	closeMusic();
	std::random_device rnd;
	std::mt19937_64 gen(rnd());
	std::uniform_int_distribution<> distrib(1, static_cast<int>(MusicResources::music.size()));

	int randomIndex = 0;
	do
	{
		randomIndex = distrib(gen);
	} while (randomIndex == curIndex && totalNum != 1);
	curIndex = randomIndex;
	playMusic();
}
