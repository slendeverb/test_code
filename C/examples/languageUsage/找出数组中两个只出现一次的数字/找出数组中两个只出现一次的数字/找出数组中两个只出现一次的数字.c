#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

void Find(int arr[],int sz,int *x,int *y)
{
	//1.Ҫ�������������
	int i = 0;
	int ret = 0;
	for (i = 0; i < sz; i++)
	{
		ret ^= arr[i];
	}
	int pos = 0;
	for (i = 0; i < 32; i++)
	{
		if (((ret >> i) & 1) == 1)
		{
			pos = i;
			break;
		}
	}
	//�Ѵӵ�λ����λ��posλΪ1�ķ���һ���飬Ϊ0�ķ�����һ����
	//int num1 = 0;
	//int num2 = 0;
	for (i = 0; i < sz; i++)
	{
		if (((arr[i] >> pos) & 1) == 1)
		{
			*x ^= arr[i];
		}
		else
		{
			*y ^= arr[i];
		}
	}
	//printf("%d %d\n", num1, num2);
}
int main()
{
	int arr[] = { 1,2,3,4,5,6,1,2,3,4 };
	int sz = sizeof(arr) / sizeof(arr[0]);
	int x = 0, y = 0;
	Find(arr,sz,&x,&y);
	printf("%d %d\n", x, y);
	return 0;
}