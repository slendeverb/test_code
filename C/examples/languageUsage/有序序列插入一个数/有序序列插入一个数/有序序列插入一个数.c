#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
//��һ�������������У���С�������򣬽�һ��������������뵽�����У���֤����������������Ȼ������
//��һ������һ��������(0=<N<=50)
//�ڶ�������N���������е������ÿո�ָ�
//������������Ҫ�����һ������
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