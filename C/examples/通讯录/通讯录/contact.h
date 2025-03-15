#pragma once

#include<stdio.h>
#include<string.h>
#include<stdlib.h>

#define MAX_NAME 20
#define MAX_SEX 10
#define MAX_PHONE 12
#define MAX_ADDRESS 30
#define MAX 1000
#define DEFAULT_SZ 3
#define INC_SZ 3

typedef struct PeoInfo
{
	char name[MAX_NAME];
	char sex[MAX_SEX];
	int age;
	char phone[MAX_PHONE];
	char address[MAX_ADDRESS];

}PeoInfo;

//通讯录-静态版本
//typedef struct Contact
//{
//	PeoInfo data[MAX];//存放添加进来的人的信息
//	int sz;//记录当前通讯录中有效信息的个数
//}Contact;

typedef struct Contact
{
	PeoInfo *data;//指向动态开辟的空间，用于存放联系人的信息
	int sz;//记录当前通讯录中有效信息的个数
	int capacity;//记录当前通讯录的最大容量
}Contact;

//初始化通讯录
void InitContact(Contact* pc);

//增加人的信息
void AddContact(Contact* pc);

//打印联系人信息
void PrintContact(const Contact* pc);

//删除联系人信息
void DeleteContact(Contact* pc);

//查找联系人
void SearchContact(Contact* pc);

//修改联系人信息
void ModifyContact(Contact* pc);

//联系人排序
void SortContact(Contact* pc);

//销毁通讯录
void DestroyContact(Contact* pc);

//将通讯录信息保存到文件中
void SaveContact(Contact* pc);

//加载通讯录
void LoadContact(Contact* pc);

//通讯录扩容
void CheckCapacity(Contact* pc);