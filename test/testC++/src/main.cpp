#include "header.h"

int main(int argc,char** argv) {
    cv::Mat image=cv::imread("C:/Users/slendeverb/Pictures/Saved Pictures/117541346_p0_master1200.jpg");
    cv::imshow("image",image);
    cv::waitKey();

    torch::Tensor tensor=torch::rand({10,10});
    if(torch::cuda::is_available()) {
        auto tensor_cuda=tensor.cuda();
        std::cout<<"tensor_cuda:"<<tensor_cuda<<std::endl;
    }
}
