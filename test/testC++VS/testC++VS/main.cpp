import std;
using namespace std;



int main(int argc, char** argv) {
	std::string s{ "不要呼呼呼，要哐哐哐" };
	for (const auto& c : s) {
		std::print("{:b}", c);
	}
	std::print("\n");
}