#pragma once

/**
 *cpp_type_name
 *CudaAllocator
 *parallel_for_kernel
 *parallel_sum_kernel parallel_sum
 *parallel_transpose_kernel
 *read_image write_image
 *parallel_xblur_kernel parallel_yblur_kernel
 *parallel_jacobi_kernel parallel_jacobi
 */

#include <string>
#include <typeinfo>
#include <cuda_runtime.h>

#include <cuda/CudaArray.cuh>

#include <cuda_helper/helper_cuda.h>

#define STB_IMAGE_IMPLEMENTATION
#include "stb_image/stb_image.h"
#define STB_IMAGE_WRITE_IMPLEMENTATION
#include "stb_image/stb_image_write.h"

#if defined(__GNUC__) || defined(__clang__)
#include <cxxabi.h>
#endif

template<typename T>
std::string cpp_type_name() {
    const char *name = typeid(T).name();
#if defined(__GNUC__) || defined(__clang__)
    int status;
    char* p = abi::__cxa_demangle(name, 0, 0, &status);
    std::string s = p;
    std::free(p);
#else
    std::string s = name;
#endif
    if (std::is_const_v<std::remove_reference_t<T> >) {
        s += " const";
    }
    if (std::is_volatile_v<std::remove_reference_t<T> >) {
        s += " volatile";
    }
    if (std::is_lvalue_reference_v<T>) {
        s += " &";
    }
    if (std::is_rvalue_reference_v<T>) {
        s += " &&";
    }
    return s;
}

#define SHOW(x) std::cout << cpp_type_name<x>() << "\n";

template <typename T>
class CudaAllocator {
public:
    using value_type = T;
    using pointer = T*;
    using const_pointer = const T*;
    using reference = T&;
    using const_reference = const T&;
    using size_type = std::size_t;
    using difference_type = std::ptrdiff_t;
    using propagate_on_container_swap = std::true_type;
    using propagate_on_container_move_assignment = std::true_type;

    template <typename U>
        struct rebind {
        using other = CudaAllocator<U>;
    };

    CudaAllocator() = default;

    T *allocate(size_t size) {
        T *ptr = nullptr;
        checkCudaErrors(cudaMallocManaged(&ptr, size * sizeof(T)));
        return ptr;
    }

    void deallocate(T *ptr, size_t size = 0) {
        checkCudaErrors(cudaFree(ptr));
    }

    template <class ...Args>
    void construct(T *p, Args &&...args) {
        if constexpr (!(sizeof...(Args) == 0 && std::is_pod_v<T>))
            ::new((void *)p) T(std::forward<Args>(args)...);
    }

    template <class U> // cihou shabi wendous
    constexpr CudaAllocator(CudaAllocator<U> const &other) noexcept {
    }

    constexpr bool operator==(CudaAllocator<T> const &other) const {
        return this == &other;
    }
};

template<typename Func>
__global__ void parallel_for_kernel(int n, Func func) {
    for (int i = blockIdx.x * blockDim.x + threadIdx.x; i < n; i += blockDim.x * gridDim.x) {
        func(i);
    }
}

template<typename T>
__global__ void parallel_sum_kernel(T *sum, const T *arr, int n) {
    T local_sum{};
    for (int i = blockIdx.x * blockDim.x + threadIdx.x; i < n; i += blockDim.x * gridDim.x) {
        local_sum += arr[i];
    }
    atomicAdd(&sum[0], local_sum);
}

// 仅供学习使用
template<int blockSize, typename T>
__global__ void parallel_sum_kernel(T *sum, const T *arr, int n) {
    __shared__ volatile T local_sum[blockSize];
    int j = threadIdx.x;
    int i = blockIdx.x;
    T temp_sum{};
    for (int t = i * blockSize + j; t < n; t += blockSize * gridDim.x) {
        temp_sum += arr[t];
    }
    local_sum[j] = temp_sum;
    __syncthreads();
    if constexpr (blockSize >= 1024) {
        if (j < 512) {
            local_sum[j] += local_sum[j + 512];
        }
        __syncthreads();
    }
    if constexpr (blockSize >= 512) {
        if (j < 256) {
            local_sum[j] += local_sum[j + 256];
        }
        __syncthreads();
    }
    if constexpr (blockSize >= 256) {
        if (j < 128) {
            local_sum[j] += local_sum[j + 128];
        }
        __syncthreads();
    }
    if constexpr (blockSize >= 128) {
        if (j < 64) {
            local_sum[j] += local_sum[j + 64];
        }
        __syncthreads();
    }
    if (j < 32) {
        if constexpr (blockSize >= 64) {
            local_sum[j] += local_sum[j + 32];
        }
        if constexpr (blockSize >= 32) {
            local_sum[j] += local_sum[j + 16];
        }
        if constexpr (blockSize >= 16) {
            local_sum[j] += local_sum[j + 8];
        }
        if constexpr (blockSize >= 8) {
            local_sum[j] += local_sum[j + 4];
        }
        if constexpr (blockSize >= 4) {
            local_sum[j] += local_sum[j + 2];
        }
        if (j == 0) {
            sum[i] = local_sum[0] + local_sum[1];
        }
    }
}

// 仅供学习使用
template<int reduceScale = 4096, int blockSize = 256, typename T>
T parallel_sum(const T *arr, int n) {
    std::vector<T, CudaAllocator<T> > sum(n / reduceScale,0);
    parallel_sum_kernel<blockSize><<<n / reduceScale,blockSize>>>(sum.data(), arr, n);
    checkCudaErrors(cudaDeviceSynchronize());
    T final_sum{};
    for (int i = 0; i < n/reduceScale; i++) {
        final_sum += sum[i];
    }
    return final_sum;
}

template <int blockSize, typename T>
__global__ void parallel_transpose_kernel(T *out, T const *in, int nx, int ny) {
    int x = blockIdx.x * blockSize + threadIdx.x;
    int y = blockIdx.y * blockSize + threadIdx.y;
    if (x >= nx || y >= ny) return;
    __shared__ T tmp[(blockSize+1) * blockSize];
    int rx = blockIdx.y * blockSize + threadIdx.x;
    int ry = blockIdx.x * blockSize + threadIdx.y;
    tmp[threadIdx.y * (blockSize+1) + threadIdx.x] = in[ry * nx + rx];
    __syncthreads();
    out[y * nx + x] = tmp[threadIdx.x * (blockSize+1) + threadIdx.y];
}

template <typename T>
__global__ void parallel_transpose_kernel(T *out, T const *in, int nx, int ny) {
    int x = blockIdx.x * blockDim.x + threadIdx.x;
    int y = blockIdx.y * blockDim.y + threadIdx.y;
    if (x >= nx || y >= ny) return;
    out[y * nx + x] = in[x * ny + y];
}

template <typename A>
std::tuple<int, int, int> read_image(A &a, const char *path) {
    int nx = 0, ny = 0, comp = 0;
    unsigned char *p = stbi_load(path, &nx, &ny, &comp, 0);
    if (!p) {
        perror(path);
        exit(-1);
    }
    a.resize(nx * ny * comp);
    for (int c = 0; c < comp; c++) {
        for (int y = 0; y < ny; y++) {
            for (int x = 0; x < nx; x++) {
                a[c * nx * ny + y * nx + x] = (1.f / 255.f) * p[(y * nx + x) * comp + c];
            }
        }
    }
    stbi_image_free(p);
    return {nx, ny, comp};
}

template <typename A>
void write_image(A const &a, int nx, int ny, int comp, const char *path) {
    auto p = (unsigned char *)malloc(nx * ny * comp);
    for (int c = 0; c < comp; c++) {
        for (int y = 0; y < ny; y++) {
            for (int x = 0; x < nx; x++) {
                p[(y * nx + x) * comp + c] = std::max(0.f, std::min(255.f, a[c * nx * ny + y * nx + x] * 255.f));
            }
        }
    }
    int ret = 0;
    auto pt = strrchr(path, '.');
    if (pt && !strcmp(pt, ".png")) {
        ret = stbi_write_png(path, nx, ny, comp, p, 0);
    } else if (pt && !strcmp(pt, ".jpg")) {
        ret = stbi_write_jpg(path, nx, ny, comp, p, 0);
    } else {
        ret = stbi_write_bmp(path, nx, ny, comp, p);
    }
    free(p);
    if (!ret) {
        perror(path);
        exit(-1);
    }
}

template <int nblur, int blockSize>
__global__ void parallel_xblur_kernel(float *out, float const *in, int nx, int ny) {
    int x = blockIdx.x * blockSize + threadIdx.x;
    int y = blockIdx.y * blockSize + threadIdx.y;
    if (x >= nx || y >= ny) return;
    float sum = 0;
    for (int i = 0; i < nblur; i++) {
        sum += in[y * nx + std::min(x + i, nx - 1)];
    }
    out[y * nx + x] = sum / nblur;
}

template <int nblur, int blockSize>
__global__ void parallel_yblur_kernel(float *out, float const *in, int nx, int ny) {
    int x = blockIdx.x * blockSize + threadIdx.x;
    int y = blockIdx.y * blockSize + threadIdx.y;
    if (x >= nx || y >= ny) return;
    float sum = 0;
    for (int i = 0; i < nblur; i++) {
        sum += in[std::min(y + i, ny - 1) * nx + x];
    }
    out[y * nx + x] = sum / nblur;
}

template <int iters, int blockSize>
__global__ void parallel_jacobi_kernel(float *out, float const *in, int nx, int ny) {
    int blockX = blockIdx.x;
    int blockY = blockIdx.y;
    int threadX = threadIdx.x;
    int threadY = threadIdx.y;
    constexpr int chunkSize = blockSize - iters * 2;
    int globalX = blockX * chunkSize - iters + threadX;
    int globalY = blockY * chunkSize - iters + threadY;

    __shared__ float mem[2][blockSize + 2][blockSize + 2];
    int clampedX = std::min(std::max(globalX, 0), nx - 1);
    int clampedY = std::min(std::max(globalY, 0), ny - 1);
    mem[0][1 + threadY][1 + threadX] = in[nx * clampedY + clampedX];

    if (threadY == 0) {
        int clampedYn = std::min(std::max(blockY * chunkSize - iters - 1, 0), ny - 1);
        mem[0][0][1 + threadX] = in[nx * clampedYn + clampedX];
        int clampedYp = std::min(std::max(blockY * chunkSize - iters + blockSize, 0), ny - 1);
        mem[0][1 + blockSize][1 + threadX] = in[nx * clampedYp + clampedX];
    }

    if (threadX == 0) {
        int clampedXn = std::min(std::max(blockX * chunkSize - iters - 1, 0), nx - 1);
        mem[0][1 + threadY][0] = in[nx * clampedY + clampedXn];
        int clampedXp = std::min(std::max(blockX * chunkSize - iters + blockSize, 0), nx - 1);
        mem[0][1 + threadY][1 + blockSize] = in[nx * clampedY + clampedXp];
    }

    __syncthreads();

    for (int stage = 0; stage < iters; stage += 2) {
#pragma unroll
        for (int phase = 0; phase < 2; phase++) {
            mem[1 - phase][1 + threadY][1 + threadX] =
                ( mem[phase][1 + threadY + 1][1 + threadX]
                + mem[phase][1 + threadY - 1][1 + threadX]
                + mem[phase][1 + threadY][1 + threadX + 1]
                + mem[phase][1 + threadY][1 + threadX - 1]
                ) / 4;
            __syncthreads();
        }
    }

    if (threadX >= iters && threadX < blockSize - iters)
        if (threadY >= iters && threadY < blockSize - iters)
            if (globalX < nx && globalY < ny)
                out[globalY * nx + globalX] = mem[0][1 + threadY][1 + threadX];
}

template <int iters, int blockSize>
void parallel_jacobi(float *out, float const *in, int nx, int ny) {
    constexpr int chunkSize = blockSize - iters * 2;
    static_assert(chunkSize > 0 && iters % 2 == 0);
    parallel_jacobi_kernel<iters, blockSize>
        <<<dim3((nx + chunkSize - 1) / chunkSize, (ny + chunkSize - 1) / chunkSize, 1),
        dim3(blockSize, blockSize, 1)>>>(out, in, nx, ny);
}