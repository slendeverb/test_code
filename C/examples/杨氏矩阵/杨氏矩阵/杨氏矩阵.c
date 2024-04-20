#define _CRT_SECURE_NO_WARNINGS 1
//有一个数字矩阵，矩阵的每行从左到右是递增的，矩阵从上到下是递增的，在矩阵中查找某一个数字是否存在
//要求时间复杂度小于O(N)
//假设矩阵为：
//1 2 3
//4 5 6
//7 8 9
//要查找数字7

//思路：
//从右上角或左下角开始找起
//如果找的数小于k,则删去一行
//如果找的数大于k,则删去一列
#include <stdio.h>

int find_num(int arr[3][3], int* px, int* py, int k)
{
    int x = 0;
    int y = *py - 1;
    while (x < *px && y >= 0)
    {
        if (arr[x][y] < k)
        {
            x++;
        }
        else if (arr[x][y] > k)
        {
            y--;
        }
        else
        {
            *px = x;
            *py = y;
            return 1;
        }
    }
    return 0;
}
int main()
{
    int x = 3, y = 3;
    int k = 7;
    int arr[3][3] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    //时间复杂度太高
    /*int i=0,j=0;
    for(i=0;i<3;i++)
    {
        for(j=0;j<3;j++)
        {
            if(arr[i][j]==k)
            {
                printf("找到了\n");
            }
            else
            {
                printf("找不到\n");
            }
        }
    }*/
    int ret = find_num(arr, &x, &y, k);
    if (ret == 1)
    {
        printf("找到了\n");
        printf("下表是:%d %d", x, y);
    }
    else
    {
        printf("找不到\n");
    }
    return 0;
}