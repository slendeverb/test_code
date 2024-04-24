# cmake最低版本要求
cmake_minimum_required(VERSION 3.28)
# 项目名称
project(testC__)
# 设置C++标准
set(CMAKE_CXX_STANDARD 23)
#包含目录
include_directories("C:/opencv/build/include")
include_directories("C:/opencv/build/include/opencv2")
# 库目录
link_directories("C:/opencv/build/x64/vc16/lib")
# 生成可执行文件
add_executable(testC__ main.cpp)
# 链接静态库
target_link_libraries(testC__ opencv_world480d.lib)
