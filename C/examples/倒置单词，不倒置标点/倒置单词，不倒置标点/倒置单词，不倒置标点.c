#include <stdio.h>
#include <string.h>
void Reverse(char* left, char* right)
{
	while (left < right)
	{
		char tmp = *left;
		*left = *right;
		*right = tmp;
		left++;
		right--;
	}
}
int main()
{
	char arr[100] = { 0 };
	gets(arr);
	int len = strlen(arr);
	Reverse(arr, arr + len - 1);
	char* start = arr;
	while (*start != '\0')
	{
		char* end = start;
		while (*end != ' ' && *end != '\0')
		{
			end++;
		}
		Reverse(start, end - 1);
		if (*end == ' ')
		{
			start = end + 1;
		}
		else
		{
			start = end;
		}
	}
	printf("%s\n", arr);
	return 0;
}