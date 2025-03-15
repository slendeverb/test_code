#include <stdio.h>

int check_sys()
{
	int a = 1;
	char* pa = (char*)&a;
	return *pa;
}
int main()
{
	int a = 1;
	int ret = check_sys();
	if (ret == 1)
	{
		printf("Ð¡¶Ë\n");
	}
	else
	{
		printf("´ó¶Ë\n");
	}
}