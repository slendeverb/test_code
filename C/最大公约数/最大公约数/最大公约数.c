#define _CRT_SECURE_NO_WARNINGS 1
#include <stdio.h>

int main()
{
	int a = 0;
	int b = 0;
	scanf("%d %d", &a, &b);
	int max = a > b ? b : a;
	while (1)
	{
		if (a % max == 0 && b % max == 0)
		{
			printf("最大公约数是：%d\n", max);
			break;
		}
		max--;
	}
	return 0;
}