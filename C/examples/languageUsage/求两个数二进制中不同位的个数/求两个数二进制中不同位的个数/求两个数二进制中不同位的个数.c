#include<stdio.h>

int NumberOf1(int n)
{
	int count = 0;
	while (n)
	{
		n = n & (n - 1);
		count++;
	}
	return count;
}
int main()
{
	int m = 0;
	int n = 0;
	int count = 0;
	scanf("%d %d", &m, &n);
	/*int i = 0;
	for (i = 0; i < 32; i++)
	{
		if (((m >> i) & 1) != ((n >> i) & 1))
		{
			count++;
		}
	}*/
	int ret = m ^ n;
	count = NumberOf1(ret);
	printf("%d\n", count);
	return 0;
}