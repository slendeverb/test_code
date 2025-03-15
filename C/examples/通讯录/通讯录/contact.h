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

//ͨѶ¼-��̬�汾
//typedef struct Contact
//{
//	PeoInfo data[MAX];//�����ӽ������˵���Ϣ
//	int sz;//��¼��ǰͨѶ¼����Ч��Ϣ�ĸ���
//}Contact;

typedef struct Contact
{
	PeoInfo *data;//ָ��̬���ٵĿռ䣬���ڴ����ϵ�˵���Ϣ
	int sz;//��¼��ǰͨѶ¼����Ч��Ϣ�ĸ���
	int capacity;//��¼��ǰͨѶ¼���������
}Contact;

//��ʼ��ͨѶ¼
void InitContact(Contact* pc);

//�����˵���Ϣ
void AddContact(Contact* pc);

//��ӡ��ϵ����Ϣ
void PrintContact(const Contact* pc);

//ɾ����ϵ����Ϣ
void DeleteContact(Contact* pc);

//������ϵ��
void SearchContact(Contact* pc);

//�޸���ϵ����Ϣ
void ModifyContact(Contact* pc);

//��ϵ������
void SortContact(Contact* pc);

//����ͨѶ¼
void DestroyContact(Contact* pc);

//��ͨѶ¼��Ϣ���浽�ļ���
void SaveContact(Contact* pc);

//����ͨѶ¼
void LoadContact(Contact* pc);

//ͨѶ¼����
void CheckCapacity(Contact* pc);