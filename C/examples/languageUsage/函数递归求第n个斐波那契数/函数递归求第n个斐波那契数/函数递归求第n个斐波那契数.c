#include <stdio.h>

int fib(int x)
{
	if (x <= 2)
		return 1;
	else
		return fib(x - 1) + fib(x - 2);
}
int main()
{
	int num = 0;
	scanf("%d", &num);
	printf("%d\n", fib(num));
	return 0;
}