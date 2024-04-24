cmake_minimum_required(VERSION 3.28)
project(testC__)
set(CMAKE_CXX_STANDARD 20)

set(OpenCV_DIR C:/opencv/build)
find_package(OpenCV REQUIRED)
include_directories(${OpenCV_INCLUDE_DIRS})
#include_directories("C:/opencv/build/include/opencv2")
link_directories(${OpenCV_LIBS})

#link_directories("C:/Program Files/NVIDIA Corporation/NvToolsExt/lib")

set(Torch_DIR C:/libtorch-win-shared-with-deps-debug-2.2.2+cu121)
find_package(Torch REQUIRED)
set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${TORCH_CXX_FLAGS})

add_executable(testC__ main.cpp)
target_link_libraries(testC__ ${TORCH_LIBRARIES} ${OpenCV_LIBS})
set_property(TARGET testC__ PROPERTY CXX_STANDARD 20)
