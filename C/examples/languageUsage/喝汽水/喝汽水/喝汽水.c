#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

int main()
{
    int money = 0;
    scanf("%d", &money);
   /* int total = money;
    int empty = money;
    while (empty >= 2)
    {
        total += empty / 2;
        empty = empty / 2 + empty % 2;
    }*/
    int total = 0;
    if (money > 0)
    {
        total = 2 * money - 1;
    }
    printf("%d\n", total);
    return 0;
}