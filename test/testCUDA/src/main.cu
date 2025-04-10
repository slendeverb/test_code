#include "header.h"

int main() {
    thrust::universal_vector<float> in;
    thrust::universal_vector<float> out;
    auto [nx,ny,comp]=read_image(in,"../../sources/img/original.jpg");
    out.resize(in.size());

    TICK(parallel_jacobi);
    constexpr int iters=4;
    for (int step=0;step<256;step+=iters) {
        parallel_jacobi<iters,32>(out.data().get(),in.data().get(),nx,ny);
        thrust::swap(out,in);
    }
    checkCudaErrors(cudaDeviceSynchronize());
    TOCK(parallel_jacobi);

    write_image(in,nx,ny,1,"../../sources/img/out.png");
    cv::Mat image=cv::imread("../../sources/img/out.png");
    int new_width=600;
    int new_height=image.rows/((float)image.cols/new_width);
    cv::Mat resized_image;
    cv::resize(image,resized_image,cv::Size(new_width,new_height));
    cv::imshow("resized_image",resized_image);
    cv::waitKey(0);
}