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

//��̬�汾
//void InitContact(Contact* pc)
//{
//	pc->sz = 0;
//	memset(pc->data, 0, sizeof(pc->data));
//	//����pc->data={0};
//}

//��̬�汾-��ʼ��
void LoadContact(Contact* pc)
{
	FILE* pf = fopen("contact.dat", "r");
	if (pf == NULL)
	{
		perror("LoadContact fopen");
		return;
	}
	//���ļ�
	PeoInfo tmp = { 0 };
	while (fread(&tmp, sizeof(PeoInfo), 1, pf))
	{
		CheckCapacity(pc);
		pc->data[pc->sz] = tmp;
		pc->sz++;
	}
	//�ر��ļ�
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

//��̬�汾
//void AddContact(Contact* pc)
//{
//	if (pc->sz == MAX)
//	{
//		printf("ͨѶ¼����,�޷����\n");
//		return;
//	}
//	
//	printf("����������:> ");
//	scanf("%s", pc->data[pc->sz].name);
//	printf("����������:> ");
//	scanf("%d", &(pc->data[pc->sz].age));
//	printf("�������Ա�:> ");
//	scanf("%s", pc->data[pc->sz].sex);
//	printf("������绰:> ");
//	scanf("%s", pc->data[pc->sz].phone);
//	printf("�������ַ:> ");
//	scanf("%s", pc->data[pc->sz].address);
//	pc->sz++;
//	printf("���ӳɹ�\n");
//}

//��̬�汾-������ϵ��
void CheckCapacity(Contact*pc)
{
	if (pc->sz == pc->capacity)
	{
		PeoInfo* ptr = realloc(pc->data, (pc->capacity + INC_SZ) * sizeof(PeoInfo));
		if (ptr != NULL)
		{
			pc->data = ptr;
			pc->capacity += INC_SZ;
			//printf("���ݳɹ�\n");
		}
		else
		{
			perror("AddContact");
			printf("������ϵ��ʧ��\n");
			return;
		}
	}
}

void AddContact(Contact* pc)
{
	//������������
	CheckCapacity(pc);

	printf("����������:> ");
	scanf("%s", pc->data[pc->sz].name);
	printf("����������:> ");
	scanf("%d", &(pc->data[pc->sz].age));
	printf("�������Ա�:> ");
	scanf("%s", pc->data[pc->sz].sex);
	printf("������绰:> ");
	scanf("%s", pc->data[pc->sz].phone);
	printf("�������ַ:> ");
	scanf("%s", pc->data[pc->sz].address);
	pc->sz++;
	printf("���ӳɹ�\n");
}

void PrintContact(const Contact* pc)
{
	int i = 0;
	//��ӡ����
	printf("%-20s\t%-5s\t%-5s\t%-12s\t%-20s\t\n", "����", "����", "�Ա�", "�绰", "��ַ");
	//��ӡ����
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
		printf("ͨѶ¼Ϊ��,�޷�ɾ��\n");
		return;
	}
	printf("������Ҫɾ������ϵ�˵�����:> ");
	scanf("%s", name);
	//1.����Ҫɾ������
	//��/��
	int pos = FindByName(pc,name);
	if (pos == -1)
	{
		printf("��ϵ�˲�����!\n");
		return;
	}
	//2.ɾ��
	int i = 0;
	for (i = pos; i < pc->sz-1; i++)
	{
		pc->data[i] = pc->data[i + 1];
	}
	pc->sz--;
	printf("��ϵ����ɾ��\n");
}

void SearchContact(Contact* pc)
{
	char name[MAX];
	printf("������Ҫ���ҵ���ϵ��\n");
	scanf("%s", name);
	int pos = FindByName(pc, name);
	if (pos == -1)
	{
		printf("Ҫ���ҵ��˲�����\n");
		return;
	}
	else
	{
		printf("%-20s\t%-5s\t%-5s\t%-12s\t%-20s\t\n", "����", "����", "�Ա�", "�绰", "��ַ");
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
	printf("������Ҫ�޸ĵ���ϵ�˵�����:> ");
	scanf("%s", name);
	int pos = FindByName(pc, name);
	if (pos == -1)
	{
		printf("Ҫ�޸���Ϣ����ϵ�˲�����\n");
		return;
	}
	else
	{
		printf("����������:> ");
		scanf("%s", pc->data[pc->sz].name);
		printf("����������:> ");
		scanf("%d", &(pc->data[pc->sz].age));
		printf("�������Ա�:> ");
		scanf("%s", pc->data[pc->sz].sex);
		printf("������绰:> ");
		scanf("%s", pc->data[pc->sz].phone);
		printf("�������ַ:> ");
		scanf("%s", pc->data[pc->sz].address);
		printf("�޸ĳɹ�\n");
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
	printf("����ɹ�\n");
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
	//д�ļ�
	int i = 0;
	for (i = 0; i < pc->sz; i++)
	{
		fwrite(pc->data + i, sizeof(PeoInfo), 1, pf);
	}
	//�ر��ļ�
	fclose(pf);
	pf = NULL;
}