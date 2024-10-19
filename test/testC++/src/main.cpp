#include "header.h"

class Solution {
    public:
    static int dulplicateNumbersXOR(std::vector<int> &&nums) {
        std::unordered_set<int> set;
        int answer = 0;
        for(const auto &num : nums) {
            if(set.contains(num)) {
                answer^=num;
            } else {
                set.insert(num);
            }
        }
        return answer;
    }
};

int main(int argc,char** argv) {
    const auto start=std::chrono::high_resolution_clock::now();
    auto ans=Solution::dulplicateNumbersXOR({1,1,2,3});
    std::println("{}",ans);
    const auto end=std::chrono::high_resolution_clock::now();
    auto duration=std::chrono::duration_cast<std::chrono::microseconds>(end-start);
    std::println("Time elapsed: {}",duration);
}