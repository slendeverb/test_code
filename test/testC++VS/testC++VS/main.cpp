import std;

class Solution {
public:
	int duplicateNumbersXOR(std::vector<int>& nums) {
		std::unordered_set<int, int> s;
		int ans = 0;
		for (const auto& x : nums) {
			if (s.contains(x)) {
				ans ^= x;
			}
			else {
				s.insert(x);
			}
		}
		return ans;
	}
};

int main(int argc, char** argv) {
	std::vector<int> nums{ 1,2,1,3 };
	std::cout << std::format("{}\n", Solution{}.duplicateNumbersXOR(nums));
}