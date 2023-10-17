#include<stdio.h>

int my_strlen(char* str)
{
	if (*str == '\0')
		return 0;
	else
		return 1 + my_strlen(str + 1);
}

void reverse_String(char* str)
{
	char tmp = *str;
	int len = my_strlen(str);
	*str = *(str + len - 1);
	*(str + len - 1) = '\0';
	if (my_strlen(str + 1) >= 2)
	{
		reverse_String(str + 1);
	}
	*(str + len - 1) = tmp;
}

int main()
{
	char arr[] = "abcdef";
	reverse_String(arr);
	printf("%s", arr);
	return 0;
}