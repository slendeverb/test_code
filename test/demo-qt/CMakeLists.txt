cmake_minimum_required(VERSION 3.14)
 
project(demo_qt LANGUAGES CXX)
 
set(CMAKE_INCLUDE_CURRENT_DIR ON)
 
set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

include_directories(
    ${PROJECT_SOURCE_DIR}/include
    ${PROJECT_SOURCE_DIR}/ui
)
 
# 开启c++17支持
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(QT NAMES Qt6 Qt5 COMPONENTS Core REQUIRED)
find_package(Qt${QT_VERSION_MAJOR} COMPONENTS Core REQUIRED)
find_package(Qt${QT_VERSION_MAJOR} COMPONENTS Network REQUIRED)
find_package(Qt${QT_VERSION_MAJOR} COMPONENTS Widgets REQUIRED)
find_package(Qt${QT_VERSION_MAJOR} COMPONENTS Charts REQUIRED)

# 查找当前目录下的所有源文件，并将名称保存到 SRC_LIST 变量
file(GLOB SRC_LIST ${CMAKE_CURRENT_SOURCE_DIR}/src/*.cpp)
file(GLOB UI_SRC_LIST ${CMAKE_CURRENT_SOURCE_DIR}/ui/*.cpp)

# 将源代码添加到此项目的可执行文件。
add_executable(${PROJECT_NAME}
    ${SRC_LIST}
    ${UI_SRC_LIST}
)

# 连接设置
target_link_libraries(${PROJECT_NAME}
    Qt${QT_VERSION_MAJOR}::Core
    Qt${QT_VERSION_MAJOR}::Widgets
    Qt${QT_VERSION_MAJOR}::Network
    Qt${QT_VERSION_MAJOR}::Charts
)

set_property(TARGET ${PROJECT_NAME} PROPERTY CXX_STANDARD 20)
