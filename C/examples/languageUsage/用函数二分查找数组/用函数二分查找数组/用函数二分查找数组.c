#include <stdio.h>

int binary_search(int arr[], int x, int y)
{
	int left = 0;
	int right = y - 1;

	while (left <= right)
	{
		int mid = (left + right) / 2;
		if (arr[mid] > x)
		{
			right = mid - 1;
		}
		else if (arr[mid] < x)
		{
			left = mid + 1;
		}
		else
		{
			return mid;
		}
	}
	return -1;
}
int main()
{
	int arr[] = { 1,2,3,4,5,6,7,8,9,10 };
	int key = 7;
	int sz = sizeof(arr) / sizeof(arr[0]);
	int ret = binary_search(arr, key, sz);
	if (ret == -1)
	{
		printf("找不到\n");
	}
	else
	{
		printf("找到了，下标是：%d\n", ret);
	}
	return 0;
}