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

	cout << "--------------------�����ֲ�������---------------------" << endl;
	cout << "\t\t0.�˳�" << endl;
	cout << "\t\t1.��������" << endl;
	cout << "\t\t2.��ͣ" << endl;
	cout << "\t\t3.����" << endl;
	cout << "\t\t4.��һ��" << endl;
	cout << "\t\t5.��һ��" << endl;
	cout << "\t\t6.�������" << endl;
	cout << "--------------------------------------------------------" << endl;
}

void MusicPlayer::keyDown()
{
	int userKey = 0;
	// ��ʾ�赥-->��Դ����
	showMusic();
	std::cout << "���������ѡ��:";
	std::cin >> userKey;
	switch (userKey)
	{
	case static_cast<int>(Operation::EXIT):
		closeMusic();
		exit(0);
		break;
	case static_cast<int>(Operation::PLAY):
		playMusic();
		break;
	case static_cast<int>(Operation::PAUSE):
		pauseMusic();
		break;
	case static_cast<int>(Operation::RESUME):
		resumeMusic();
		break;
	case static_cast<int>(Operation::NEXT_ONE):
		nextMusic();
		break;
	case static_cast<int>(Operation::PREV_ONE):
		prevMusic();
		break;
	case static_cast<int>(Operation::RANDOM_PLAY):
		randomPlay();
		break;
	default:
		break;
	}
}

void MusicPlayer::showMusic()
{
	std::cout << "�赥:" << std::endl;
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
	mciSendString(openCmd.c_str(), NULL, 0, NULL);
	std::string playCmd = cmd[static_cast<int>(CMD::PLAY)] + MusicResources::music[curIndex];
	mciSendString(playCmd.c_str(), NULL, 0, NULL);
}

void MusicPlayer::pauseMusic()
{
	std::string pauseCmd = cmd[static_cast<int>(CMD::PAUSE)] + MusicResources::music[curIndex];
	mciSendString(pauseCmd.c_str(), NULL, 0, NULL);
}

void MusicPlayer::resumeMusic()
{
	std::string resumeCmd = cmd[static_cast<int>(CMD::RESUME)] + MusicResources::music[curIndex];
	mciSendString(resumeCmd.c_str(), NULL, 0, NULL);
}

void MusicPlayer::closeMusic()
{
	std::string closeCmd = cmd[static_cast<int>(CMD::CLOSE)] + MusicResources::music[curIndex];
	mciSendString(closeCmd.c_str(), NULL, 0, NULL);
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