#include<stdio.h>

int main()
{
	int n = 0;
	int a = 0;
	scanf("%d %d", &n, &a);
	int i = 0;
	int ret = 0;
	int sum = 0;
	for (i = 0; i < n; i++)
	{
		ret = ret * 10 + a;
		sum += ret;
	}
	printf("%d\n", sum);
	return 0;
}