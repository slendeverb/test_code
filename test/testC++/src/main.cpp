#include "header.h"

class Solution {
    public:
    int smallestRangeII(std::vector<int>& nums, int k) {
        std::ranges::sort(nums);
        int ans=nums.back()-nums.front();
        for(int i=1;i<nums.size();i++) {
            int mx=std::max(nums[i-1]+k,nums.back()-k);
            int mn=std::min(nums.front()+k,nums[i]-k);
            ans=std::min(ans,mx-mn);
        }
        return ans;
    }
};

int main(int argc,char** argv) {
    const auto start=std::chrono::high_resolution_clock::now();
    std::vector<int> nums{7,8,8,5,2};
    int k{4};
    auto ans=Solution{}.smallestRangeII(nums,k);
    std::println("{}",ans);
    const auto end=std::chrono::high_resolution_clock::now();
    auto duration=std::chrono::duration_cast<std::chrono::microseconds>(end-start);
    std::println("Time elapsed: {}",duration);
}