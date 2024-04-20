#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
struct A
{
	int a;
	short b;
	int c;
	char d;
};

#define OFFSETOF(struct_name,mem_name) (int)&(((struct_name*)0)->mem_name)

int main()
{
	printf("%d\n", OFFSETOF(struct A, a));
	printf("%d\n", OFFSETOF(struct A, b));
	printf("%d\n", OFFSETOF(struct A, c));
	printf("%d\n", OFFSETOF(struct A, d));
	return 0;
}