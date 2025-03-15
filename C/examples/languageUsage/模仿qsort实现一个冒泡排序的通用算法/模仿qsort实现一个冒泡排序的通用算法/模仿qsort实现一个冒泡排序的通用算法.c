#define _CRT_SECURE_NO_WARNINGS 1
#include <stdio.h>
#include<string.h>
struct Stu
{
    char name[20];
    int age;
}s[3];

int sort_by_age(const void* e1, const void* e2)
{
    return ((struct Stu*)e1)->age - ((struct Stu*)e2)->age;
}

int sort_by_name(const void* e1, const void* e2)
{
    return strcmp(((struct Stu*)e1)->name, ((struct Stu*)e2)->name);
}

void Print_int(int arr[], int sz)
{
    int i = 0;
    for (i = 0; i < sz; i++)
    {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

Swap(char* buf1, char* buf2, size_t width)
{
    int i = 0;
    for (i = 0; i < width; i++)
    {
        char tmp = *buf1;
        *buf1 = *buf2;
        *buf2 = tmp;
        buf1++;
        buf2++;
    }
}

int cmp_int(const void* e1, const void* e2)
{
    return *((int*)e1) - *((int*)e2);
}

void bubble_sort(void* base, size_t sz, size_t width, int (*cmp)(const void* e1, const void* e2))
{
    int i = 0;
    for (i = 0; i < sz - 1; i++)
    {
        int j = 0;
        for (j = 0; j < sz - 1 - i; j++)
        {
            if (cmp((char*)base + j * width, (char*)base + (j + 1) * width) > 0)
            {
                Swap((char*)base + j * width, (char*)base + (j + 1) * width, width);
            }
        }
    }
}

int main()
{
    int arr[10] = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    int sz_arr = sizeof(arr) / sizeof(arr[0]);
    int sz_s = sizeof(s) / sizeof(s[0]);
    struct Stu s[3] = { {"zhangsan",30},{"lisi",35},{"wangwu",25} };
    bubble_sort(arr, sz_arr, sizeof(arr[0]), cmp_int);
    bubble_sort(s, sz_s, sizeof(s[0]), sort_by_age);
    bubble_sort(s, sz_s, sizeof(s[0]), sort_by_name);
    Print_int(arr,sz_arr);

    return 0;
}