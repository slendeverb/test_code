#include"game.h"

//初始化棋盘
void init_board(char board[ROWS][COLS], int rows, int cols, char set)
{
	int i = 0;
	for (i = 0; i < rows; i++)
	{
		int j = 0;
		for (j = 0; j < cols; j++)
		{
			board[i][j] = set;
		}
	}
}

//设置地雷
void set_mine(char mine[ROWS][COLS], int row, int col)
{
	int count = COUNT;
	while (count)
	{
		int x = rand() % row + 1;
		int y = rand() % col + 1;
		if (mine[x][y] == '0')
		{
			mine[x][y] = '1';
			count--;
		}
	}
}

//打印棋盘
void display_board(char board[ROWS][COLS], int row, int col)
{
	int i = 0;
	printf("---------------扫雷游戏----------------\n");
	//打印列号
	for (i = 0; i <= row; i++)
	{
		printf(" %d  ", i);
	}
	printf("\n");
	printf("\n");
	for (i = 1; i <= row; i++)
	{
		int j = 0;
		//打印行号
		printf(" %d  ", i);
		//打印棋子行
		for (j = 1; j <= col; j++)
		{
			printf(" %c ", board[i][j]);
			if (j <= col - 1)
			{
				printf("|");
			}
		}
		printf("\n");
		//打印分隔行
		if (i <= row - 1)
		{
			printf("    ");
			for (j = 1; j <= col; j++)
			{
				printf("---");
				if (j <= col - 1)
				{
					printf("|");
				}
			}
			printf("\n");
		}
	}
	printf("---------------扫雷游戏----------------\n");
}

//计算周围一圈范围内存在地雷的个数
int get_mine_count(char mine[ROWS][COLS], int x, int y)
{
	//把3×3方格内所有的字符（‘1’或‘0’）加起来，然后再统一减去9个字符‘0’，得到的结果就是地雷的个数
	int i = 0;
	int sum = 0;
	for (i = x - 1; i <= x + 1; i++)
	{
		int j = 0;
		for (j = y - 1; j <= y + 1; j++)
		{
			sum += mine[i][j];
		}
	}
	return (sum - 9 * '0');
}

//把所有mine中地雷全部显示到show上
void show_all_mine(char mine[ROWS][COLS], char show[ROWS][COLS], int row, int col)
{
	int i = 0;
	for (i = 1; i <= row; i++)
	{
		int j = 0;
		for (j = 1; j <= col; j++)
		{
			if (mine[i][j] == '1')
			{
				show[i][j] = '@';//地雷用字符‘@’在用户界面表示
			}
		}
	}
}

//炸金花式展开函数
void explode_spread(char mine[ROWS][COLS], char show[ROWS][COLS], int row, int col, int x, int y, int* ret)
{
	//限制非法坐标的展开
	if (x >= 1 && x <= row && y >= 1 && y <= col)
	{
		//计算该位置附近四周地雷的个数
		int count = get_mine_count(mine, x, y);
		//若四周没有一个地雷，则需要向该位置的四周展开，直到展开到某个位置附近存在地雷为止
		if (count == 0)
		{
			//把附近没有地雷的位置变成字符 “空格”
			show[x][y] = ' ';
			int i = 0;
			//向四周共8个位置递归调用
			for (i = x - 1; i <= x + 1; i++)
			{
				int j = 0;
				for (j = y - 1; j <= y + 1; j++)
				{
					//限制对点位置的重复展开调用，使得每一个位置只能向四周展开一次
					if (show[i][j] == '*')
					{
						explode_spread(mine, show, row, col, i, j, ret);
					}
				}
			}
		}
		//若四周存在地雷则应该在这个位置上标注上地雷的个数
		else
		{
			show[x][y] = count + '0';
			(*ret)++;
		}
	}
}

//标记地雷位置（方便排查游戏中的地雷）
void sign_mine(char show[ROWS][COLS], int row, int col)
{
	int x = 0;
	int y = 0;
	while (1)
	{
		printf("请输入要标记的坐标：");
		scanf("%d%d", &x, &y);
		if (x >= 1 && x <= row && y >= 1 && y <= col)
		{
			if (show[x][y] == '*')
			{
				show[x][y] = '!';
				break;
			}
			else
			{
				printf("该位置不能被标记，请重新输入：\n");
			}
		}
		else
		{
			printf("输入坐标非法，请重新输入:\n");
		}
	}
	system("cls");
	display_board(show, row, col);
}

//win加上周围没有雷的格子
int already_win(char mine[ROWS][COLS], char show[ROWS][COLS], int row, int col)
{
	int ret = 0;
	for (int i = 1; i <= row; i++)
	{
		for (int j = 1; j <= col; j++)
		{
			int count = get_mine_count(mine, i, j);
			if (count == 0)
			{
				ret++;
			}
		}
	}
	return ret;
}

//开始扫雷
void find_mind(char mine[ROWS][COLS], char show[ROWS][COLS], int row, int col)
{
	int x = 0;
	int y = 0;
	int win = already_win(mine, show, row, col);//记录排查出不是地雷位置的个数
	char ch = 0;
	while (win < row * col - COUNT)
	{
		printf("请输入要排查的位置下标：");
		scanf("%d%d", &x, &y);
		if (x >= 1 && x <= row && y >= 1 && y <= col)//判断输入下标是否有效
		{
			if (mine[x][y] == '1')//排查到了地雷
			{
				break;
			}
			//此时没有排查到地雷
			else
			{
				int ret = 0;
				//炸金花式展开
				explode_spread(mine, show, row, col, x, y, &ret);
				win += ret;
				if (win == row * col - COUNT)
				{
					break;
				}
				system("cls");
				//打印棋盘
				display_board(show, row, col);
				printf("需要标注地雷就输入：Y，不需要标注地雷则输入：N\n");
				//清空一下缓冲区
				while ((ch = getchar()) != '\n');
				scanf("%c", &ch);
				switch (ch)
				{
				case 'Y':
					//标记雷的位置
					sign_mine(show, row, col);
					break;
				default:
					break;
				}
			}
		}
		else
		{
			printf("输入下标非法，请重新输入：\n");
		}
	}
	//把所有mine中地雷全部显示到show上
	show_all_mine(mine, show, row, col);
	system("cls");
	//打印棋盘
	display_board(show, row, col);
	//判断是否排查成功
	if (win >= row * col - COUNT)
	{
		printf("恭喜你排查出所有的地雷！！！\n");
	}
	else
	{
		printf("扫雷失败，你被炸死了！！！\n");
	}
	system("pause");
}
