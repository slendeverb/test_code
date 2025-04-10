cmake_minimum_required(VERSION 3.30)

set(CMAKE_CUDA_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CUDA_ARCHITECTURES "native")
#nvidia-smi --query-gpu=compute_cap --format=csv
#52;61;70;75;80;86
set(CUDA_SEPARABLE_COMPILATION ON)
project(testCUDA LANGUAGES CXX CUDA)

set(CMAKE_INCLUDE_CURRENT_DIR ON)
set(CMAKE_VERBOSE_MAKEFILE ON)

if(WIN32)
    ADD_DEFINITIONS(-D_WIN32 -DNOMINMAX -DOPENVDB_STATICLIB -DOPENEXR_DLL)
else()
    ADD_DEFINITIONS(-pthread -fPIC)
endif()

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
        ${BACKWARD_ENABLE}
)

#target_link_libraries(${PROJECT_NAME} ${CONAN_LIBS})

find_package(Threads REQUIRED)
target_link_libraries(${PROJECT_NAME} PUBLIC Threads::Threads)

find_package(OpenMP REQUIRED)
target_link_libraries(${PROJECT_NAME} PUBLIC OpenMP::OpenMP_CXX)

find_package(CUDAToolkit REQUIRED)
target_link_libraries(${PROJECT_NAME} PRIVATE CUDA::cudart)

find_package(OpenCV REQUIRED)
if(OpenCV_FOUND)
    target_include_directories(${PROJECT_NAME} PUBLIC ${OpenCV_INCLUDE_DIRS})
    target_link_libraries(${PROJECT_NAME} PRIVATE ${OpenCV_LIBS})
endif()

find_package(Backward CONFIG REQUIRED)
target_link_libraries(${PROJECT_NAME} PRIVATE Backward::Backward)

find_package(TBB CONFIG REQUIRED)
target_link_libraries(${PROJECT_NAME} PRIVATE TBB::tbb TBB::tbbmalloc TBB::tbbmalloc_proxy)

find_package(OpenGL REQUIRED)
target_link_libraries(${PROJECT_NAME} PRIVATE OpenGL::GL)
find_package(glfw3 CONFIG REQUIRED)
target_link_libraries(${PROJECT_NAME} PRIVATE glfw)
find_package(glad CONFIG REQUIRED)
target_link_libraries(${PROJECT_NAME} PRIVATE glad::glad)

if(MSVC)
    cmake_host_system_information(RESULT CPU_NUMBER_OF_LOGICAL_CORES QUERY NUMBER_OF_LOGICAL_CORES)
    add_compile_options($<$<CXX_COMPILER_ID:MSVC>:
            /MP${CPU_NUMBER_OF_LOGICAL_CORES}
            /utf-8
            >)
endif()

target_compile_options(${PROJECT_NAME} PUBLIC $<$<COMPILE_LANGUAGE:CUDA>:
        --expt-relaxed-constexpr
        --extended-lambda
        --use_fast_math
        >)

find_program(CCACHE_PROGRAM ccache)
if(CCACHE_PROGRAM)
    message(STATUS "Set up ccache ...")
    set_property(GLOBAL PROPERTY RULE_LAUNCH_COMPILE ccache)
    set_property(GLOBAL PROPERTY RULE_LAUNCH_LINK ccache)
endif()

set(CMAKE_UNITY_BUILD ON)
set_target_properties(${PROJECT_NAME} PROPERTIES UNITY_BUILD ON)

include(CheckIPOSupported)
check_ipo_supported(RESULT _IsIPOSupported)
if(_IsIPOSupported)
    message(STATUS "Turn on INTERPROCEDURAL_OPTIMIZATION")
    set(CMAKE_INTERPROCEDURAL_OPTIMIZATION TRUE)
endif()

target_precompile_headers(${PROJECT_NAME} PRIVATE
        ${CMAKE_CURRENT_SOURCE_DIR}/include/header.h
        ${CMAKE_CURRENT_SOURCE_DIR}/include/my_utils.h
        ${CMAKE_CURRENT_SOURCE_DIR}/include/cuda/CudaArray.cuh
        ${CMAKE_CURRENT_SOURCE_DIR}/include/cuda/CudaStream.cuh
        ${CMAKE_CURRENT_SOURCE_DIR}/include/cuda_helper/helper_cuda.h
        ${CMAKE_CURRENT_SOURCE_DIR}/include/cuda_helper/helper_string.h
        ${CMAKE_CURRENT_SOURCE_DIR}/include/stb_image/stb_image.h
        ${CMAKE_CURRENT_SOURCE_DIR}/include/stb_image/stb_image_write.h
)
