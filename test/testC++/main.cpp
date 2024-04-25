#include <format>
#include <iostream>

int main() {
    std::cout<<std::format("{}","Hello world").c_str()<<std::endl;
    return 0;
}
