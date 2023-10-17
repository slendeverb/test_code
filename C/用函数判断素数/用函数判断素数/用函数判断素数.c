#include <stdio.h>
#include <math.h>

int is_prime(int x)
{
	int i = 0;
	for (i = 2; i <= sqrt(x); i++)
	{
		if (x % i == 0)
		{
			return 0;
		}
	}
	if (x == 2)
	{
		return 1;
	}
	return 1;
}
int main()
{
	int num = 0;
	scanf("%d", &num);
	if (is_prime(num) == 1)
	{
		printf("是素数\n");
	}
	else
	{
		printf("不是素数\n");
	}
	return 0;
}