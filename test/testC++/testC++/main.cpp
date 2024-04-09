#include <algorithm>
#include <any>
#include <array>
#include <atomic>
#include <barrier>
#include <bit>
#include <bitset>
#include <charconv>
#include <chrono>
#include <cmath>
#include <compare>
#include <complex>
#include <concepts>
#include <condition_variable>
#include <cstdarg>
#include <cstddef>
#include <cstdint>
#include <cstdio>
#include <cstdlib>
#include <ctime>
#include <deque>
#include <exception>
#include <filesystem>
#include <format>
#include <forward_list>
#include <fstream>
#include <functional>
#include <future>
#include <initializer_list>
#include <iomanip>
#include <iostream>
#include <iterator>
#include <latch>
#include <limits>
#include <list>
#include <locale>
#include <map>
#include <memory>
#include <mutex>
#include <new>
#include <numbers>
#include <numeric>
#include <optional>
#include <queue>
#include <random>
#include <ranges>
#include <ratio>
#include <regex>
#include <semaphore>
#include <set>
#include <source_location>
#include <span>
#include <sstream>
#include <stack>
#include <stdexcept>
#include <string>
#include <string_view>
#include <thread>
#include <tuple>
#include <type_traits>
#include <unordered_map>
#include <unordered_set>
#include <utility>
#include <valarray>
#include <variant>
#include <vector>
#include <version>
#define NOMINMAX
#include <windows.h>
using namespace std;

const int N{ 105 };
int n;
int a[N];
int vis[N];

void solve() {
    cin >> n;
    for (int i = 1; i <= n; ++i) {
        cin >> a[i];
    }
    int ans = 0;
    for (int i = 1; i <= n; ++i) {
        memset(vis, 0, sizeof(vis));
        int num = 0;
        int pos = i - 1;
        int count = 0;
        while (num <= n&&count<n) {
            ++pos;
            if (pos > n) {
                pos = 1;
            }
            if (vis[pos]) {
                continue;
            }
            ++num;
            if (a[pos] == num) {
                vis[pos] = 1;
                num = 0;
                count++;
            }
        }
        ans = std::max(ans, count);
    }
    cout << ans << "\n";
}

int main() {
    std::ifstream in{ "./in.txt" };
    std::streambuf* oldIn{ std::cin.rdbuf(in.rdbuf()) };
    std::ofstream out{ "./out.txt" };
    std::streambuf* oldOut{ std::cout.rdbuf(out.rdbuf()) };
    std::ofstream err{ "./err.txt" };
    std::streambuf* oldErr{ std::cerr.rdbuf(err.rdbuf()) };
    std::streambuf* oldLog{ std::clog.rdbuf(err.rdbuf()) };

    clock_t startTime{ clock() };
    solve();
    clock_t endTime{ clock() };
    std::cout << "\ntime cost: " << endTime - startTime << std::endl;

    in.close();
    std::cin.rdbuf(oldIn);
    out.close();
    std::cout.rdbuf(oldOut);
    err.close();
    std::cerr.rdbuf(oldErr);
    std::clog.rdbuf(oldLog);
    return 0;
}