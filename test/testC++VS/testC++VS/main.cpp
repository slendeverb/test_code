import std;
using namespace std;

const int N = 105;

struct Edge {
	int v, w;
};

struct Node {
	int dis, u;
	bool operator>(const Node& a) const {
		return dis > a.dis;
	}
};

class Solution {
public:
	int networkDelayTime(vector<vector<int>>& times, int n, int k) {
		vector<vector<Edge>> e(n + 1);
		for (const auto& x : times) {
			int u = x[0];
			int v = x[1];
			int w = x[2];
			e[u].push_back({ v,w });
		}
		int dis[N], vis[N];
		priority_queue<Node, vector<Node>, greater<>> pq;
		memset(dis, 0x3f, sizeof(dis));
		memset(vis, 0, sizeof(vis));
		dis[k] = 0;
		pq.push({ 0,k });
		while (!pq.empty()) {
			int u = pq.top().u;
			pq.pop();
			if (vis[u]) {
				continue;
			}
			vis[u] = 1;
			for (const auto& ed : e[u]) {
				int v = ed.v, w = ed.w;
				if (dis[v] > dis[u] + w) {
					dis[v] = dis[u] + w;
					pq.push({ dis[v],v });
				}
			}
		}
		int ans = *max_element(dis + 1, dis + n);
		if (ans == 0x3f3f3f3f) {
			ans = -1;
		}
		return ans;
	}
};

int main() {
	vector<vector<int>> times{ {2,1,1},{2,3,1},{3,4,1} };
	int n{ 4 }, k{ 2 };
	cout << Solution().networkDelayTime(times, n, k) << "\n";
}