#include <iostream>
#include <vector>
#include <numeric>
#include <ranges>

auto maximumWealth(const auto& accounts)->int {
    return std::ranges::max(accounts
        |std::views::transform([](const auto& account){
            return std::reduce(account.begin(),account.end());
        }));
}

int main(int argc,char** argv) {
    std::vector<std::vector<int>> accounts{{1,2,3},{4,5,6},{7,8,9}};
    std::cout<<maximumWealth(accounts)<<"\n";
}