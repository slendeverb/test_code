cmake_minimum_required(VERSION 3.28)
project(testC++)

set(CMAKE_CXX_STANDARD 20)

set(OpenCV_DIR C:/opencv/build)
find_package(OpenCV REQUIRED)
include_directories(${OpenCV_INCLUDE_LIBS})
link_directories(${OpenCV_LIBS})

#set(CMAKE_PREFIX_PATH C:/libtorch-win-shared-with-deps-debug-2.2.2+cu121)
#find_package(Torch REQUIRED)
#set(CMAKE_CXX_FLAGS ${CMAKE_CXX_FLAGS} ${TORCH_CXX_FLAGS})

add_executable(testC++ main.cpp)
target_link_libraries(testC++ ${OpenCV_LIBS})
#target_link_libraries(TransGANpp ${TORCH_LIBRARIES})
set_property(TARGET testC++ PROPERTY CXX_STANDARD 20)
