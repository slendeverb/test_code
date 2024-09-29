#include "header.h"

int main(int argc,char** argv) {
    cv::Mat image=cv::imread("C:/Users/slendeverb/Pictures/Saved Pictures/117541346_p0_master1200.jpg");
    cv::Mat resized_image;
    int down_width=800.0/1200*800;
    int down_height=800;
    cv::resize(image,resized_image,cv::Size(down_width,down_height));
    cv::imshow("image",resized_image);
    cv::waitKey();

    torch::Tensor tensor=torch::rand({10,10});
    if(torch::cuda::is_available()) {
        auto tensor_cuda=tensor.cuda();
        std::cout<<"tensor_cuda:"<<tensor_cuda<<std::endl;
    }
}
