#include <stdio.h>

int Factorial(int x)
{
	if (x <= 1)
		return 1;
	else
		return x * Factorial(x - 1);

}
int main()
{
	int n = 0;
	scanf("%d", &n);
	printf("%d\n", Factorial(n));
	return 0;
}