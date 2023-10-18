#define _CRT_SECURE_NO_WARNINGS 1
#include"contact.h"

static int FindByName(Contact*pc,char name[])
{
	int i = 0;
	for (i = 0; i < pc->sz;i++)
	{
		if (strcmp(pc->data[i].name, name)==0)
		{
			return i;
		}
	}
	return -1;
}

//静态版本
//void InitContact(Contact* pc)
//{
//	pc->sz = 0;
//	memset(pc->data, 0, sizeof(pc->data));
//	//或者pc->data={0};
//}

//动态版本-初始化
void LoadContact(Contact* pc)
{
	FILE* pf = fopen("contact.dat", "r");
	if (pf == NULL)
	{
		perror("LoadContact fopen");
		return;
	}
	//读文件
	PeoInfo tmp = { 0 };
	while (fread(&tmp, sizeof(PeoInfo), 1, pf))
	{
		CheckCapacity(pc);
		pc->data[pc->sz] = tmp;
		pc->sz++;
	}
	//关闭文件
	fclose(pf);
	pf = NULL;
}

void InitContact(Contact* pc)
{
	pc->data = (PeoInfo*)malloc(DEFAULT_SZ*sizeof(PeoInfo));
	if (pc->data == NULL)
	{
		perror("InitContact");
		return;
	}
	pc->sz = 0;
	pc->capacity = DEFAULT_SZ;
	LoadContact(pc);
}

//静态版本
//void AddContact(Contact* pc)
//{
//	if (pc->sz == MAX)
//	{
//		printf("通讯录已满,无法添加\n");
//		return;
//	}
//	
//	printf("请输入名字:> ");
//	scanf("%s", pc->data[pc->sz].name);
//	printf("请输入年龄:> ");
//	scanf("%d", &(pc->data[pc->sz].age));
//	printf("请输入性别:> ");
//	scanf("%s", pc->data[pc->sz].sex);
//	printf("请输入电话:> ");
//	scanf("%s", pc->data[pc->sz].phone);
//	printf("请输入地址:> ");
//	scanf("%s", pc->data[pc->sz].address);
//	pc->sz++;
//	printf("增加成功\n");
//}

//动态版本-增加联系人
void CheckCapacity(Contact*pc)
{
	if (pc->sz == pc->capacity)
	{
		PeoInfo* ptr = realloc(pc->data, (pc->capacity + INC_SZ) * sizeof(PeoInfo));
		if (ptr != NULL)
		{
			pc->data = ptr;
			pc->capacity += INC_SZ;
			//printf("增容成功\n");
		}
		else
		{
			perror("AddContact");
			printf("增加联系人失败\n");
			return;
		}
	}
}

void AddContact(Contact* pc)
{
	//考虑增加容量
	CheckCapacity(pc);

	printf("请输入名字:> ");
	scanf("%s", pc->data[pc->sz].name);
	printf("请输入年龄:> ");
	scanf("%d", &(pc->data[pc->sz].age));
	printf("请输入性别:> ");
	scanf("%s", pc->data[pc->sz].sex);
	printf("请输入电话:> ");
	scanf("%s", pc->data[pc->sz].phone);
	printf("请输入地址:> ");
	scanf("%s", pc->data[pc->sz].address);
	pc->sz++;
	printf("增加成功\n");
}

void PrintContact(const Contact* pc)
{
	int i = 0;
	//打印标题
	printf("%-20s\t%-5s\t%-5s\t%-12s\t%-20s\t\n", "名字", "年龄", "性别", "电话", "地址");
	//打印数据
	for (i = 0; i < pc->sz; i++)
	{
		printf("%-20s\t%-5d\t%-5s\t%-12s\t%-20s\t\n",
			pc->data[i].name, 
			pc->data[i].age, 
			pc->data[i].sex, 
			pc->data[i].phone, 
			pc->data[i].address);
	}
}

void DeleteContact(Contact* pc)
{
	char name[MAX];
	if (pc->sz == 0)
	{
		printf("通讯录为空,无法删除\n");
		return;
	}
	printf("请输入要删除的联系人的名字:> ");
	scanf("%s", name);
	//1.查找要删除的人
	//有/无
	int pos = FindByName(pc,name);
	if (pos == -1)
	{
		printf("联系人不存在!\n");
		return;
	}
	//2.删除
	int i = 0;
	for (i = pos; i < pc->sz-1; i++)
	{
		pc->data[i] = pc->data[i + 1];
	}
	pc->sz--;
	printf("联系人已删除\n");
}

void SearchContact(Contact* pc)
{
	char name[MAX];
	printf("请输入要查找的联系人\n");
	scanf("%s", name);
	int pos = FindByName(pc, name);
	if (pos == -1)
	{
		printf("要查找的人不存在\n");
		return;
	}
	else
	{
		printf("%-20s\t%-5s\t%-5s\t%-12s\t%-20s\t\n", "名字", "年龄", "性别", "电话", "地址");
		printf("%-20s\t%-5d\t%-5s\t%-12s\t%-20s\t\n", 
			pc->data[pos].name, 
			pc->data[pos].age, 
			pc->data[pos].sex, 
			pc->data[pos].phone, 
			pc->data[pos].address);
	}
}

void ModifyContact(Contact* pc)
{
	char name[MAX];
	printf("请输入要修改的联系人的名字:> ");
	scanf("%s", name);
	int pos = FindByName(pc, name);
	if (pos == -1)
	{
		printf("要修改信息的联系人不存在\n");
		return;
	}
	else
	{
		printf("请输入名字:> ");
		scanf("%s", pc->data[pc->sz].name);
		printf("请输入年龄:> ");
		scanf("%d", &(pc->data[pc->sz].age));
		printf("请输入性别:> ");
		scanf("%s", pc->data[pc->sz].sex);
		printf("请输入电话:> ");
		scanf("%s", pc->data[pc->sz].phone);
		printf("请输入地址:> ");
		scanf("%s", pc->data[pc->sz].address);
		printf("修改成功\n");
	}
}

void SortContact(Contact* pc)
{
	int i = 0;
	for (i = 0; i < pc->sz-1; i++)
	{
		if (strcmp(pc->data[i].name, pc->data[i+1].name) > 0)
		{
			PeoInfo tmp[MAX];
			tmp[i] = pc->data[i];
			pc->data[i] = pc->data[i + 1];
			pc->data[i + 1];
			tmp[i];
		}
	}
	printf("排序成功\n");
}

void DestroyContact(Contact* pc)
{
	free(pc->data);
	pc->data = NULL;
	pc->sz = 0;
	pc->capacity = 0;
}

void SaveContact(Contact* pc)
{
	FILE *pf=fopen("contact.dat", "w");
	if (pf == NULL)
	{
		perror("SaveContact fopen");
		return;
	}
	//写文件
	int i = 0;
	for (i = 0; i < pc->sz; i++)
	{
		fwrite(pc->data + i, sizeof(PeoInfo), 1, pf);
	}
	//关闭文件
	fclose(pf);
	pf = NULL;
}