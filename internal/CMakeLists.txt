cmake_minimum_required(VERSION 3.9)
project(llvm4s-jni)

set(CMAKE_CXX_STANDARD 14)

find_package(JNI REQUIRED)
find_package(Java REQUIRED)
find_package(LLVM 5.0 REQUIRED CONFIG)

llvm_map_components_to_libnames(llvm_libs support core irreader)

include_directories(${JAVA_INCLUDE_PATH})
include_directories(${JAVA_INCLUDE_PATH2})
include_directories(${LLVM_INCLUDE_DIRS})
include_directories(src/main/include)
include_directories(src/main/jni-include)

aux_source_directory(src/main/c++ SOURCES)
aux_source_directory(src/main/c++/ir IR_SOURCES)

add_library(llvm4s SHARED ${SOURCES} ${IR_SOURCES})

target_link_libraries(llvm4s ${llvm_libs})