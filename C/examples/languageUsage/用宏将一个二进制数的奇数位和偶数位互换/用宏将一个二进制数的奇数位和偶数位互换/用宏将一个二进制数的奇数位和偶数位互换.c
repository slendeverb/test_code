#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
#define SWAP(x) ((x&0xaaaaaaaa)>>1)+((x&0x55555555)<<1)

int main()
{
	int num = 10;
	int ret = SWAP(num);
	printf("%d\n", ret);
	return 0;
}