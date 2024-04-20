#include<stdio.h>

int main()
{
	int a = 0;
	int i = 0;
	scanf("%d", &a);
	//获取奇数位
	for (i = 31; i >= 1; i -= 2)
	{
		printf("%d ", (a >> i) & 1);
	}
	printf("\n");
	//获取偶数位
	for (i = 30; i >= 0; i -= 2)
	{
		printf("%d ", (a >> i) & 1);
	}
	return 0;
}