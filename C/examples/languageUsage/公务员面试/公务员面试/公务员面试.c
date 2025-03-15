#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
//公务员面试现场打分，有七位考官，从键盘输入若干组成绩，每组七个分数（百分制），去掉一个最高分和一个最低分，输出每组的平均成绩
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