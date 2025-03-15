#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

int main()
{
    //A说：不是我。
    //B说：是C。
    //C说：是D。
    //D说：C在胡说。
    //已知3个人说了真话，1个人说了假话
    char killer = 0;
    for (killer = 'A'; killer <= 'D'; killer++)
    {
        if ((killer != 'A') + (killer == 'C') + (killer == 'D') + (killer != 'D') == 3)
        {
            printf("%c\n", killer);
        }
    }
    return 0;
}