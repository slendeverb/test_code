//#include"game.h"
//
//void menu()
//{
//	printf("1.play\n");
//	printf("0.exit\n");
//}
//
//void game()
//{
//	char mine[ROWS][COLS] = { 0 };//存放布置好的雷的信息
//	char show[ROWS][COLS] = { 0 };//存放排查出的雷的信息
//	
//	//初始化棋盘
//	InitBoard(mine, ROWS, COLS, '0');
//	InitBoard(show, ROWS, COLS, '*');
//	
//	//打印棋盘
//	DisplayBoard(show, ROW, COL);
//	
//	//布置雷
//	SetMine(mine, ROW, COL);
//	//DisplayBoard(mine, ROW, COL);
//	
//	//排查雷
//	FindMine(mine,show,ROW,COL);
//
//
//}
//
//int main()
//{
//	int input = 0;
//	srand((unsigned int)time(NULL));
//	do 
//	{
//		menu();
//		printf("请选择：");
//		scanf("%d", &input);
//		switch(input)
//		{
//		case 1:
//			printf("游戏开始！\n");
//			game();//扫雷游戏
//			break;
//		case 0:
//			printf("退出游戏\n");
//			break;
//		default:
//			printf("输入无效，请重新输入\n");
//			break;
//		}
//	} while (input);
//	
//	return 0;
//}

#include"game.h"

void menu()
{
	printf("*****************************************\n");
	printf("*********  1.play      0.exit   *********\n");
	printf("*****************************************\n");
}

void game()
{
	//存放地雷的棋盘
	char mine[ROWS][COLS] = { 0 };
	//展示给用户看的棋盘
	char show[ROWS][COLS] = { 0 };
	//初始化棋盘
	init_board(mine, ROWS, COLS, '0');
	init_board(show, ROWS, COLS, '*');
	//放置地雷
	set_mine(mine, ROW, COL);
	system("cls");//清空屏幕
	//打印棋盘
	//display_board(mine, ROW, COL);
	display_board(show, ROW, COL);
	//开始扫雷
	find_mind(mine, show, ROW, COL);
}

int main()
{
	//设置随机数的起点
	srand((unsigned int)time(NULL));
	int input = 0;
	do
	{
		menu();//菜单
		printf("请选择：");
		scanf("%d", &input);
		switch (input)
		{
		case 1:
			game();
			break;
		case 0:
			printf("退出游戏\n");
			break;
		default:
			printf("输入错误，请重新输入\n");
			break;
		}
	} while (input);
	return 0;
}
