#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
#include<ctype.h>
#include<limits.h>

enum State
{
	INVALID,
	VALID
};

enum State state = INVALID;
//state ��¼����my_atoi ����ֵ�ǺϷ����ǷǷ�

int my_atoi(const char* s)
{
	int flag = 1;
	//��ָ��
	if (s == NULL)
	{
		return 0;
	}
	//���ַ���
	if (*s == "\0")
	{
		return 0;
	}
	//�����հ��ַ�
	while (isspace(*s))
	{
		s++;
	}
	//������
	if (*s == '+')
	{
		flag = 1;
		s++;
	}
	else if(*s=='-')
	{
		flag = -1;
		s++;
	}
	//���������ַ�
	long long n = 0;
	while (isdigit(*s))
	{
		n = n * 10 + flag*( * s - '0');
		if (n > INT_MAX||n<INT_MIN)
		{
			return 0;
		}
		s++;
	}
	if (*s == '\0')
	{
		state = VALID;
		return (int)n;
	}
	else
	{
		return (int)n;
	}
}
int main()
{
	//���⣺
	//1.��ָ��
	//2.���ַ���
	//3.�����Ƿ��ַ�
	//4.�������η�Χ

	const char* p = "1234";
	int ret = my_atoi(p);
	if (state == VALID)
	{
		printf("%d\n", ret);
	}
	return 0;
}