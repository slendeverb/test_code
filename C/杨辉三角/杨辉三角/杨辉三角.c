#define _CRT_SECURE_NO_WARNINGS 1
#include <stdio.h>

int main()
{
    int arr[10][10] = { 0 };
    int i = 0;
    int j = 0;
    for (i = 0; i < 10; i++)
    {
        for (j = 0; j <= i; j++)
        {
            if (j == 0)
            {
                arr[i][j] = 1;
            }
            if (i == j)
            {
                arr[i][j] = 1;
            }
            if (i >= 2 && j >= 1)
            {
                arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
            }
        }
    }
    for (i = 0; i < 10; i++)
    {
        for (j = 0; j <= i; j++)
        {
            printf("%d ", arr[i][j]);
        }
        printf("\n");
    }
    return 0;
}

//#include <stdio.h>
//int main()
//{
//    int n = 0;
//    printf("请输入杨辉三角的行数：");
//    // n <= 13
//    scanf("%d", &n);
//    int i = 0;
//    for (i = 1; i <= n; i++)
//    {
//        long long int ret = 1;
//        int j = 0;
//        for (j = (n + 30) - 2 * i; j > 0; j--)
//        {
//            printf(" ");
//        }
//        printf("%4d", 1);
//        for (j = 2; j <= i; j++)
//        {
//            ret = ret * (i - j + 1) / (j - 1);
//            printf("%4lld", ret);
//        }
//        printf("\n");
//    }
//    return 0;
//}