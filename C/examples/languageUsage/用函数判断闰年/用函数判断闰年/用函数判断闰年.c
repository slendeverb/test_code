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
		printf("������\n");
	}
	else
	{
		printf("��������\n");
	}
	return 0;
}