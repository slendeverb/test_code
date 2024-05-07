# CMakeList.txt: testC++VSCMake 的 CMake 项目，在此处包括源代码并定义
# 项目特定的逻辑。
#
cmake_minimum_required(VERSION 3.28)
project(testC++VSCMake)

set(CMAKE_CXX_STANDARD 20)

set(OpenCV_DIR C:/opencv/build)
find_package(OpenCV REQUIRED)
include_directories(${OpenCV_INCLUDE_LIBS})
link_directories(${OpenCV_LIBS})

find_package(Torch REQUIRED)

# 将源代码添加到此项目的可执行文件。
add_executable (testC++VSCMake "testC++VSCMake.cpp" "testC++VSCMake.h")
target_link_libraries(testC++VSCMake "${OpenCV_LIBS}" "${TORCH_LIBRARIES}")
set_property(TARGET testC++VSCMake PROPERTY CXX_STANDARD 20)

if (CMAKE_VERSION VERSION_GREATER 3.12)
  set_property(TARGET testC++VSCMake PROPERTY CXX_STANDARD 20)
endif()

# TODO: 如有需要，请添加测试并安装目标。
