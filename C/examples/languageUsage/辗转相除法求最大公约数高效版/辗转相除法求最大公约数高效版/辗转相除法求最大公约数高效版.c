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
	printf("���Լ��:%d\n", n);
	return 0;
}