#define _CRT_SECURE_NO_WARNINGS 1
//写一个函数，判断一个字符串是否为另一个字符串旋转之后的字符串
//例如：给定s1=AABCD和s2=BCDAA，返回1
//给定s1=abcd和s2=ABCD，返回0
#include <stdio.h>
#include <string.h>

int is_string_rotate(char* str1, char* str2)
{
    if (strlen(str1) != strlen(str2))
    {
        return 0;
    }
    int len = strlen(str1);
    /*int i = 0;
    for (i = 0; i < len; i++)
    {
        char tmp = *str1;
        int j = 0;
        for (j = 0; j < len - 1; j++)
        {
            *(str1 + j) = *(str1 + j + 1);
        }
        *(str1 + len - 1) = tmp;
        if (strcmp(str1, str2) == 0)
        {
            return 1;
        }
    }
    return 0;*/
    strncat(str1, str1, len);
    char* ret = strstr(str1, str2);
    return ret != NULL;
}
int main()
{
    char arr1[20] = "AABCD";
    char arr2[20] = "BCDAA";
    int ret = is_string_rotate(arr1, arr2);
    if (ret == 1)
    {
        printf("yes\n");
    }
    else
    {
        printf("no\n");
    }
    return 0;
}