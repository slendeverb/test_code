#include "header.h"

int main(int argc,char** argv) {
    cv::Mat image=cv::imread("C:/Users/slendeverb/Pictures/Saved Pictures/117541346_p0_master1200.jpg");
    cv::Mat resized_image;
    int resized_cols=1080*0.9;
    int resized_rows=;
    cv::resize(image,resized_image,cv::Size(resized_rows,resized_cols));
    cv::imshow("image",resized_image);
    cv::waitKey();

    torch::Tensor tensor=torch::rand({10,10});
    if(torch::cuda::is_available()) {
        auto tensor_cuda=tensor.cuda();
        std::cout<<"tensor_cuda:"<<tensor_cuda<<std::endl;
    }
}
