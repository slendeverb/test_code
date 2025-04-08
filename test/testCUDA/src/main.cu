#include "header.h"

__host__ __device__ void say_hello() {
#ifdef __CUDA_ARCH__
    printf("Hello World from GPU architecture %d!\n",__CUDA_ARCH__);
#else
    printf("Hello World from CPU!\n");
#endif
}

__global__ void kernel() {
    say_hello();
}

int main() {
    kernel<<<1,1>>>();
    cudaDeviceSynchronize();
    say_hello();
    std::unordered_set<int> ust;
    auto x=ust.insert(1).first;
    SHOW(decltype(x));
}