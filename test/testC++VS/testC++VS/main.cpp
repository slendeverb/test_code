#include <string>
#include <fstream>
#include <iostream>

void trimString(std::string& str) {
	if (!str.empty()) {
		str.erase(0, str.find_first_not_of(" "));
		str.erase(str.find_last_not_of(" ") + 1);
	}
}

std::string getLocalIP() {
	std::string ip("127.0.0.1");
	std::string ipconfig_content;

	FILE* fp = _popen("ipconfig", "r");
	if (NULL != fp) {
		char line[4096];
		while (NULL != fgets(line, sizeof(line), fp)) {
			ipconfig_content += line;
		}
		auto p = ipconfig_content.rfind("IPv4");
		if (p != std::string::npos) {
			auto p2 = ipconfig_content.find(":", p);
			if (p2 != std::string::npos) {
				auto p3 = ipconfig_content.find("\n", p2);
				if (p3 != std::string::npos) {
					ip = ipconfig_content.substr(p2 + 1, p3 - p2 - 1);
					trimString(ip);
				}
			}
		}
	}
	if (fp) {
		_pclose(fp);
	}
	return ip;
}

int main(int argc, char** argv) {
	std::ofstream out;
	try {
		out.open("D:/Virtual Machines/share/ip.txt");
		if (out.is_open()) {
			std::cout << "file open succeeded\n";
			out << getLocalIP() << std::endl;
		}
		else {
			std::cout << "file open failed\n";
		}
	}
	catch (std::exception& e) {
		std::cout << e.what() << std::endl;
	}
	if (out.is_open()) {
		out.close();
	}
}