#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>

int main()
{
    //A˵�������ҡ�
    //B˵����C��
    //C˵����D��
    //D˵��C�ں�˵��
    //��֪3����˵���滰��1����˵�˼ٻ�
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