#include<stdio.h>

int main()
{
	int a = 0, b = 0;
	scanf("%d %d", &a, &b);
	/*int min = a > b ? a : b;
	while (1)
	{
		if (min % a == 0 && min % b == 0)
		{
			printf("%d\n", min);
			break;
		}
		min++;
	}*/
	int i = 0;
	for (i = 1;; i++)
	{
		if (a * i % b == 0)
		{
			printf("%d\n", a * i);
			break;
		}
	}
	return 0;
}