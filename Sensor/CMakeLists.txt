cmake_minimum_required(VERSION 3.3)
project(Sensor)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY  "${CMAKE_CURRENT_SOURCE_DIR}/bin")



add_subdirectory(LibSensor)

set(SOURCE_FILES main.cpp)
add_executable(Sensor ${SOURCE_FILES})



target_link_libraries(Sensor CPUSensor)
target_link_libraries(Sensor boost_system)
target_link_libraries(Sensor pthread)
target_link_libraries(Sensor sigar)
target_link_libraries(Sensor libjsoncpp.a)
target_link_libraries(Sensor libyaml-cpp.a)

