#include"game.h"

void menu()
{
	printf("******************************\n");
	printf("*********   1.play   *********\n");
	printf("*********   0.exit   *********\n");
	printf("******************************\n");
}

void game()
{
	char board[ROW][COL] = { 0 };
	InitBoard(board,ROW,COL);
	DisplayInitBoard(board, ROW, COL);
	char ret = 0;
	while(1)
	{
		PlayerMove(board,ROW,COL);
		DisplayInitBoard(board, ROW, COL);
		ret = Is_Win(board, ROW, COL);
		if (ret != 'C')
		{
			break;
		}
		ComputerMove(board, ROW, COL);
		Sleep(1000);
		DisplayInitBoard(board, ROW, COL);
		ret = Is_Win(board, ROW, COL);
		if (ret != 'C')
		{
			break;
		}
	}
	if (ret == '*')
	{
		printf("��һ�ʤ\n");
	}
	else if (ret == '#')
	{
		printf("���Ի�ʤ\n");
	}
	else
	{
		printf("ƽ��\n");
	}
	DisplayInitBoard(board, ROW, COL);
	Sleep(1000);
}

int main()
{
	int input = 0;
	srand((unsigned int)time(NULL));
	do
	{
		menu();
		printf("��ѡ��");
		scanf("%d", &input);
		switch (input)
		{
		case 1:
			printf("��������Ϸ��ʼ!\n");
			game();
			break;
		case 0:
			printf("�˳���Ϸ\n");
			break;
		default:
			printf("ѡ�����������ѡ��");
			break;
		}
	} while (input);
	
	return 0;
}