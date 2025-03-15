#include <stdio.h>

int main()
{
	int m, n, t = 0;
	scanf("%d%d", &m, &n);
	while (t = m % n)
	{
		m = n;
		n = t;
	}
	printf("最大公约数:%d\n", n);
	return 0;
}