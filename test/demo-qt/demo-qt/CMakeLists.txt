# CMakeList.txt: demo-qt 的 CMake 项目，在此处包括源代码并定义
# 项目特定的逻辑。
#
cmake_minimum_required(VERSION 3.14)
 
project(demo-qt LANGUAGES CXX)
 
set(CMAKE_INCLUDE_CURRENT_DIR ON)
 
set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

include_directories(
    ./include
    ./ui/main-window
)
 
# 开启c++17支持
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(QT NAMES Qt6 Qt5 COMPONENTS Core REQUIRED)
find_package(Qt${QT_VERSION_MAJOR} COMPONENTS Core REQUIRED)
find_package(Qt${QT_VERSION_MAJOR} COMPONENTS Network REQUIRED)
find_package(Qt${QT_VERSION_MAJOR} COMPONENTS Widgets REQUIRED)

# 将源代码添加到此项目的可执行文件。
add_executable(demo-qt
    ./src/demo-qt.cpp
    ./ui/main-window/MainWindow.cpp
)

# 连接设置
target_link_libraries(demo-qt
    Qt${QT_VERSION_MAJOR}::Core
    Qt${QT_VERSION_MAJOR}::Widgets
    Qt${QT_VERSION_MAJOR}::Network
)

#if (CMAKE_VERSION VERSION_GREATER 3.12)
#  set_property(TARGET demo-qt PROPERTY CXX_STANDARD 20)
#endif()

# TODO: 如有需要，请添加测试并安装目标。
