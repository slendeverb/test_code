#define _CRT_SECURE_NO_WARNINGS 1
#include <stdio.h>
#include <string.h>
#include <windows.h>
#include <time.h>
#include <stdlib.h>

void menu()
{
	printf("1.play\n");
	printf("2.exit\n");
}
void game()
{
	int guess = 0;
	int ret = rand() % 100 + 1;
	while (1)
	{
		printf("������֣�");
		scanf("%d", &guess);
		if (guess < ret)
		{
			printf("��С��\n");
		}
		else if (guess > ret)
		{
			printf("�´���\n");
		}
		else
		{
			printf("�¶���!\n");
			break;
		}
	}

}
int main()
{
	//��������Ϸ
	srand((unsigned int)time(NULL));
	int input = 0;

	do
	{
		menu();
		printf("��ѡ��(1 or 0):");
		scanf("%d", &input);
		switch (input)
		{
		case 1:
			game();
			break;
		case 0:
			printf("��Ϸ����\n");
			break;
		default:
			printf("������ѡ��!\n");
			break;

		}

	} while (input);
	return 0;
}
