#define _CRT_SECURE_NO_WARNINGS 1
#include"contact.h";
//ͨѶ¼-��̬�汾
//1.ͨѶ¼���ܴ��1000���˵���Ϣ
//ÿ���˵���Ϣ��
//����+����+�Ա�+�绰+��ַ
//2.�����˵���Ϣ
//3.ɾ��ָ���˵���Ϣ
//4.�޸�ָ���˵���Ϣ
//5.����ָ���˵���Ϣ
//6.����ͨѶ¼����Ϣ

//��̬�汾
//ͨѶ¼��ʼ�����ܴ�������˵���Ϣ
//���ռ����ʱ�����������ռ�

//�ɱ���汾
//�������˳�ʱ������Ϣд���ļ���
//��ͨѶ¼��ʼ��ʱ�������ļ�����Ϣ��ͨѶ¼��

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
	Contact con;//ͨѶ¼
	//��ʼ��ͨѶ¼
	//��data�ڶ���������һ��ռ�
	//sz=0
	//capacity��ʼ��Ϊ��ǰ�������
	InitContact(&con);

	do
	{
		menu();
		printf("��ѡ��:> ");
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
			//����Ϣ���浽�ļ���
			SaveContact(&con);
			//����ͨѶ¼
			DestroyContact(&con);
			printf("�˳�����\n");
			break;
		default:
			printf("�����������������\n");
			break;
		}
	} while (input);
	return 0;
}