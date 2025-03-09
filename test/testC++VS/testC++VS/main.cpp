import std;
using namespace std;

class Solution {
public:
    vector<int> maximumBeauty(vector<vector<int>>& items, vector<int>& queries) {
        std::sort(items.begin(), items.end(), [](const vector<int>& item1, const vector<int>& item2) {
            return item1[0] < item2[0];
        });
        std::vector<int> idx(queries.size());
        std::iota(idx.begin(), idx.end(), 0);
        std::sort(idx.begin(), idx.end(), [&](int i, int j) { return queries[i] < queries[j]; });

        std::vector<int> ans(queries.size());
        int max_beauty = 0, j = 0;
        for (int i : idx) {
            int q = queries[i];
            while (j < items.size() && items[j][0] <= q) {
                max_beauty = std::max(max_beauty, items[j][1]);
                j++;
            }
            ans[i] = max_beauty;
        }
        return ans;
    }
};

int main() {
	std::vector<std::vector<int>> items = { {1, 2}, {2, 3}, {3, 1}, {4, 2} };
	std::vector<int> queries = { 2, 3 };
	Solution sol;
	std::vector<int> ans = sol.maximumBeauty(items, queries);
	for (int a : ans) {
		std::cout << a << " ";
	}
	std::cout << std::endl;
}