#include <fstream>
#include <iostream>
#include <print>

void solve() {
	std::string input_path{ "./in.txt" };
	std::string output_path{ "./out.txt" };
	std::ifstream input_stream{ input_path };
	std::ofstream output_stream{ output_path };
	if (input_stream.is_open()) {
		std::println(output_stream, "{}", "input_stream is open");
	}
}

int main() {
	solve();
	return 0;
}