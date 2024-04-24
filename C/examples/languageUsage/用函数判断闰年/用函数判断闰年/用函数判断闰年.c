#include <stdio.h>

int is_leap_year(int x)
{
	return ((x % 4 == 0 && x % 100 != 0) || x % 400 == 0);
}
int main()
{
	int y = 0;
	scanf("%d", &y);
	if (is_leap_year(y) == 1)
	{
		printf("是闰年\n");
	}
	else
	{
		printf("不是闰年\n");
	}
	return 0;
}