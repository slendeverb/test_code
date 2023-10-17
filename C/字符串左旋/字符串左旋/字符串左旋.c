#define _CRT_SECURE_NO_WARNINGS 1
//题目要求：
//实现一个函数，可以左旋k个字符
//例如：
//ABCD左旋1个字符->BCDA
//ABCD左旋2个字符->CDAB
#include <stdio.h>
#include <string.h>
#include<assert.h>

void reverse(char* left, char* right)
{
    assert(left);
    assert(right);
    while (left < right)
    {
        char tmp = *left;
        *left = *right;
        *right = tmp;
        left++;
        right--;
    }
}
void string_left_rotate(char* str, int k)
{
    // int i = 0;
    // int len = strlen(str);
    // for (i = 0; i < k; i++)
    // {
    //     char tmp = *str;
    //     int j = 0;
    //     for (j = 0; j < len - 1; j++)
    //     {
    //         *(str + j) = *(str + j + 1);
    //     }
    //     *(str + len - 1) = tmp;
    // }

    //或者：
    assert(str);
    int len = strlen(str);
    reverse(str, str + k - 1);
    reverse(str + k, str + len - 1);
    reverse(str, str + len - 1);
}
int main()
{
    char arr[10] = "ABCDEF";
    int k = 2;
    string_left_rotate(arr, k);
    printf("%s\n", arr);
    return 0;
}