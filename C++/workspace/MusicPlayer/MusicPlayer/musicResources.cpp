#include "MusicResources.h"

std::map<int, std::string> MusicResources::music;

MusicResources& MusicResources::getMusicResources()
{
    static MusicResources instance;
    return instance;
}

bool MusicResources::isMusic(const std::string& name)
{
    // ��ô�ж�һ���ַ����Ľ�β��.mp3
    int length = name.size();
    // substr ��ȡ����
    return name.substr(length - 4) == ".mp3";
}

// ���ַ����еĿո��滻Ϊ_
std::string MusicResources::getNewName(std::string name)
{
    for (int i = 0; i < name.size(); i++)
    {
        if (name[i] == ' ')
        {
            name[i] = '_';
        }
    }
    return name;
}

void MusicResources::traverseAllFile()
{
    std::cout << "���������ֿ�URL:";
    std::string urlRoot;
    std::cin >> urlRoot;
    // �ж�·���Ƿ����
    std::filesystem::path url(urlRoot);
    if (!std::filesystem::exists(url))
    {
        std::cout << "URL error..............." << std::endl;
        exit(0);
    }
    // �õ���ǰ·�����������ֵ��ļ�·��
    // C++�±�׼��·������filesystem
    int pos = 0;
    std::string oldName;
    std::string newName;
    std::filesystem::directory_iterator begin(url);
    for (std::filesystem::directory_iterator end; begin != end; ++begin)
    {
        // ���������ļ��е�ʱ��
        if (!std::filesystem::is_directory(begin->path()))
        {
            // �ж��ǲ���.mp3�ļ�
            if (isMusic(begin->path().filename().string()))
            {
                // �����������ļ����еĿո��滻Ϊ_
                oldName = urlRoot + "/" + begin->path().filename().string();
                newName = getNewName(oldName);
                int result = rename(oldName.c_str(), newName.c_str());
                music[++pos] = newName;
            }
        }
    }
    if (pos == 0)
    {
        std::cout << "���ֿ���û������.........." << std::endl;
        exit(0);
    }
    else
    {
        std::cout << "���ֿ���سɹ�..............." << std::endl;
    }
}

MusicResources::MusicResources()
{
    std::cout << "��ʼ�����ֿ�.............." << std::endl;
    traverseAllFile();
}