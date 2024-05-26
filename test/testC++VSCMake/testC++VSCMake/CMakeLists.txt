# CMakeList.txt: testC++VSCMake 的 CMake 项目，在此处包括源代码并定义
# 项目特定的逻辑。
#
cmake_minimum_required(VERSION 3.28)
project(testC++VSCMake)

set(CMAKE_CXX_STANDARD 20)

#aux_source_directory(${PROJECT_BINARY_DIR}/../src SRC_LIST)
#include_directories(${PROJECT_BINARY_DIR}/../include)

add_executable(${PROJECT_NAME}
    "testC++VSCMake.cpp"
    "testC++VSCMake.h"
)

set(OpenCV_DIR "C:/opencv/build/x64/vc16/lib")
find_package(OpenCV REQUIRED)
if(OpenCV_FOUND)
    target_include_directories(${PROJECT_NAME} PUBLIC ${OpenCV_INCLUDE_DIRS})
    target_link_libraries(${PROJECT_NAME} ${OpenCV_LIBS})
 
endif()

set_property(TARGET ${PROJECT_NAME} PROPERTY CXX_STANDARD 26)

if (CMAKE_VERSION VERSION_GREATER 3.12)
  set_property(TARGET ${PROJECT_NAME} PROPERTY CXX_STANDARD 26)
endif()

# TODO: 如有需要，请添加测试并安装目标。
