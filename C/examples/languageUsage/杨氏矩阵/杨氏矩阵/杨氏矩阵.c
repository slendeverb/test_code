#define _CRT_SECURE_NO_WARNINGS 1
//��һ�����־��󣬾����ÿ�д������ǵ����ģ�������ϵ����ǵ����ģ��ھ����в���ĳһ�������Ƿ����
//Ҫ��ʱ�临�Ӷ�С��O(N)
//�������Ϊ��
//1 2 3
//4 5 6
//7 8 9
//Ҫ��������7

//˼·��
//�����Ͻǻ����½ǿ�ʼ����
//����ҵ���С��k,��ɾȥһ��
//����ҵ�������k,��ɾȥһ��
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
    //ʱ�临�Ӷ�̫��
    /*int i=0,j=0;
    for(i=0;i<3;i++)
    {
        for(j=0;j<3;j++)
        {
            if(arr[i][j]==k)
            {
                printf("�ҵ���\n");
            }
            else
            {
                printf("�Ҳ���\n");
            }
        }
    }*/
    int ret = find_num(arr, &x, &y, k);
    if (ret == 1)
    {
        printf("�ҵ���\n");
        printf("�±���:%d %d", x, y);
    }
    else
    {
        printf("�Ҳ���\n");
    }
    return 0;
}