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
		printf("玩家获胜\n");
	}
	else if (ret == '#')
	{
		printf("电脑获胜\n");
	}
	else
	{
		printf("平局\n");
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
		printf("请选择：");
		scanf("%d", &input);
		switch (input)
		{
		case 1:
			printf("三子棋游戏开始!\n");
			game();
			break;
		case 0:
			printf("退出游戏\n");
			break;
		default:
			printf("选择错误，请重新选择：");
			break;
		}
	} while (input);
	
	return 0;
}