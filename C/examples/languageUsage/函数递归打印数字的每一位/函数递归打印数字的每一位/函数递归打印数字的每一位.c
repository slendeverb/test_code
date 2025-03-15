#include <stdio.h>

void Print(unsigned int x)
{
	if (x > 9)
	{
		Print(x / 10);
	}
	printf("%d ", x % 10);
}
int main()
{
	unsigned int num = 0;
	scanf("%u", &num);
	Print(num);
	return 0;
}