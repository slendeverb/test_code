#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
//有一个有序数字序列，从小到大排序，将一个新输入的数插入到序列中，保证插入新数后，序列仍然是升序
//第一行输入一个整数，(0=<N<=50)
//第二行输入N个升序排列的数，用空格分隔
//第三行输入想要插入的一个整数
int main()
{
	int arr[51] = { 0 };
	int n = 0;
	scanf("%d", &n);
	int i = 0;
	for (i = 0; i < n; i++)
	{
		scanf("%d", &arr[i]);
	}
	int m = 0;
	scanf("%d", &m);
	for (i = n - 1; i >= 0; i--)
	{
		if (arr[i] > m)
		{
			arr[i + 1] = arr[i];
		}
		else
		{
			//arr[i + 1] = m;
			break;
		}
	}
	/*if (i < 0)
	{
		arr[0] = m;
	}*/
	arr[i + 1] = m;
	for (i = 0; i < n + 1; i++)
	{
		printf("%d ", arr[i]);
	}
	return 0;
}