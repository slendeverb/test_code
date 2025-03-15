#include <stdio.h>

void Swap(int* px, int* py)
{
	int z = *px;
	*px = *py;
	*py = z;
}
int main()
{
	int a = 10;
	int b = 20;
	printf("交换前：a=%d,b=%d\n", a, b);
	Swap(&a, &b);
	printf("交换后：a=%d,b=%d\n", a, b);
	return 0;
}