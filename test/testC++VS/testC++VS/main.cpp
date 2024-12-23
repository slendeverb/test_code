import std;
using namespace std;

class Solution {
public:
	int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
		int ans = 0;
		if (a == e && !(a == c && abs(b - f) > abs(d - f)) || b == f && !(b == d && abs(a - e) > abs(c - e)) || abs(c - e) == abs(d - f) && !(e - a == a - c && f - b == b - d)) {
			ans = 1;
		}
		else {
			ans = 2;
		}
		return ans;
	}
};

int main() {
	std::cout << Solution{}.minMovesToCaptureTheQueen(4, 5, 7, 8, 2, 3) << "\n";
}