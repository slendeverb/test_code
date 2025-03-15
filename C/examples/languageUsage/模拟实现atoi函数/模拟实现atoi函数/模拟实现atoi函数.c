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
//state 记录的是my_atoi 返回值是合法还是非法

int my_atoi(const char* s)
{
	int flag = 1;
	//空指针
	if (s == NULL)
	{
		return 0;
	}
	//空字符串
	if (*s == "\0")
	{
		return 0;
	}
	//跳过空白字符
	while (isspace(*s))
	{
		s++;
	}
	//正负号
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
	//处理数字字符
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
	//问题：
	//1.空指针
	//2.空字符串
	//3.遇到非法字符
	//4.超出整形范围

	const char* p = "1234";
	int ret = my_atoi(p);
	if (state == VALID)
	{
		printf("%d\n", ret);
	}
	return 0;
}