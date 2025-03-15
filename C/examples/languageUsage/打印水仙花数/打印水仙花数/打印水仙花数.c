#include<stdio.h>
#include<math.h>

int main()
{
	int i = 0;
	for (i = 0; i <= 100000; i++)
	{
		int n = 0;
		int tmp = i;
		while (tmp % 10 != 0)
		{
			n++;
			tmp /= 10;
		}
		int sum = 0;
		tmp = i;
		while (tmp)
		{
			sum += (int)pow(tmp % 10, n);
			tmp /= 10;
		}
		if (sum == i)
		{
			printf("%d ", i);
		}
	}
	return 0;
}