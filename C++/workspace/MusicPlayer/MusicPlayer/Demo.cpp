/*
	1.�����ļ�����-->�赥
		1.1 ��Դ����-->����ģʽʵ����Դ����
	2.����������
		2.1 �˵�
		2.2 ��������
		2.3 ʵ�ֹ���
	3.���ֲ��ţ�mciSendString("order musicURL",0,0,0)����
				ָ�open �������ļ�
					play ���������ļ�
					pause ��ͣ
					resume ����
	4.�߼�����
		4.1 ��һ�׺���һ�ױ߽紦��
		4.4 �������
*/
#include"MusicPlayer.h"
#include"MusicResources.h"

int main()
{
	MusicPlayer musicPlayer;
	while (true)
	{
		musicPlayer.menu();
		musicPlayer.keyDown();
		system("cls");
	}
	return 0;
}