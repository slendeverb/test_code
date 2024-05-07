// testC++VSCMake.cpp: 定义应用程序的入口点。
//

#include "testC++VSCMake.h"

void solve() {
	std::cout << torch::cuda::is_available() << std::endl;
}

int main()
{
	solve();
	return 0;
}
