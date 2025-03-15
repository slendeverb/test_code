#define _CRT_SECURE_NO_WARNINGS 1
#include <stdio.h>

int main()
{
	int m = 0;
	int n = 0;
	int t = 0;
	scanf("%d%d", &m, &n);
	int max = m > n ? m : n;
	int min = m > n ? n : m;
	t = min;
	while (max % min)
	{
		t = max % min;
		max = min;
		min = t;
	}
	printf("最大公约数是:%d\n", t);
	return 0;
}