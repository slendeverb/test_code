#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
//����Ա�����ֳ���֣�����λ���٣��Ӽ�������������ɼ���ÿ���߸��������ٷ��ƣ���ȥ��һ����߷ֺ�һ����ͷ֣����ÿ���ƽ���ɼ�
int main()
{
	int score = 0;
	int i = 0;
	int max = 0;
	int min = 100;
	float sum = 0;
	for (i = 0; i < 7; i++)
	{
		scanf("%d", &score);
		sum += score;
		max = max > score ? max : score;
		min = min < score ? min : score;
	}
	printf("%.2f", ((sum - max - min )/ 5.0));
	return 0;
}