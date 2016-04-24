cmake_minimum_required(VERSION 3.3)
project(Sensor)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY  "${CMAKE_CURRENT_SOURCE_DIR}/bin")

include_directories(${PROJECT_SOURCE_DIR}/include)

add_library(sensors src/CPUSensor.cpp src/DiskSensor.cpp src/MemorySensor.cpp src/NetIFSensor.cpp src/ProcSensor.cpp)
set(SOURCE_FILES main.cpp)
add_executable(Sensor ${SOURCE_FILES})



target_link_libraries(Sensor sensors)
target_link_libraries(Sensor sigar)
target_link_libraries(Sensor libjsoncpp.a)
target_link_libraries(Sensor libyaml-cpp.a)

