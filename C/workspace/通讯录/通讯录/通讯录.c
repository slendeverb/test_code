#define _CRT_SECURE_NO_WARNINGS 1
#include"contact.h";
//通讯录-静态版本
//1.通讯录中能存放1000个人的信息
//每个人的信息：
//名字+年龄+性别+电话+地址
//2.增加人的信息
//3.删除指定人的信息
//4.修改指定人的信息
//5.查找指定人的信息
//6.排序通讯录的信息

//动态版本
//通讯录初始化后，能存放三个人的信息
//当空间放满时，增加两个空间

//可保存版本
//当程序退出时，将信息写入文件中
//当通讯录初始化时，加载文件的信息到通讯录中

void menu()
{
	printf("1.add    2.delete\n");
	printf("3.search    4.modify\n");
	printf("5.sort    6.print\n");
	printf("0.exit\n");
}
enum Option
{
	EXIT,
	ADD,
	DELETE,
	SEARCH,
	MODIFY,
	SORT,
	PRINT
};
int main()
{
	int input = 0;
	Contact con;//通讯录
	//初始化通讯录
	//给data在堆区上申请一块空间
	//sz=0
	//capacity初始化为当前最大容量
	InitContact(&con);

	do
	{
		menu();
		printf("请选择:> ");
		scanf("%d", &input);
		switch (input)
		{
		case ADD:
			AddContact(&con);
			break;
		case DELETE:
			DeleteContact(&con);
			break;
		case SEARCH:
			SearchContact(&con);
			break;
		case MODIFY:
			ModifyContact(&con);
			break;
		case SORT:
			SortContact(&con);
			break;
		case PRINT:
			PrintContact(&con);
			break;
		case EXIT:
			//将信息保存到文件中
			SaveContact(&con);
			//销毁通讯录
			DestroyContact(&con);
			printf("退出程序\n");
			break;
		default:
			printf("输入错误，请重新输入\n");
			break;
		}
	} while (input);
	return 0;
}