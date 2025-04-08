cmake_minimum_required(VERSION 3.28)

set(CMAKE_CUDA_ARCHITECTURES "native")
#nvidia-smi --query-gpu=compute_cap --format=csv
#52;61;70;75;80;86
project(testCUDA LANGUAGES CXX CUDA)

set(CMAKE_CUDA_STANDARD 20)
#set(CMAKE_CXX_EXTENSIONS OFF)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(CMAKE_INCLUDE_CURRENT_DIR ON)
set(CMAKE_VERBOSE_MAKEFILE ON)

set(CMAKE_CUDA_FLAGS "${CMAKE_CUDA_FLAGS} --expt-relaxed-constexpr")

add_definitions(-DNOMINMAX)

macro(conan_basic_setup)
    set(options TARGETS NO_OUTPUT_DIRS SKIP_RPATH KEEP_RPATHS SKIP_STD SKIP_FPIC)
endmacro()

conan_basic_setup()

include_directories(
        ${PROJECT_SOURCE_DIR}/include
)

# 查找当前目录下的所有源文件，并将名称保存到 SRC_LIST 变量
file(GLOB SRC_LIST ${CMAKE_CURRENT_SOURCE_DIR}/src/*.cpp)
file(GLOB CU_SRC_LIST ${CMAKE_CURRENT_SOURCE_DIR}/src/*.cu)

# 将源代码添加到此项目的可执行文件。
add_executable(${PROJECT_NAME}
        ${SRC_LIST}
        ${CU_SRC_LIST}
        include/my_utils.h
)

#target_link_libraries(${PROJECT_NAME} ${CONAN_LIBS})

set_property(TARGET ${PROJECT_NAME} PROPERTY CXX_STANDARD 20)
set_target_properties(${PROJECT_NAME} PROPERTIES
        CUDA_SEPARABLE_COMPILATION ON)

target_precompile_headers(${PROJECT_NAME} PRIVATE ${CMAKE_CURRENT_SOURCE_DIR}/include/header.h)
target_precompile_headers(${PROJECT_NAME} PRIVATE ${CMAKE_CURRENT_SOURCE_DIR}/include/my_utils.h)