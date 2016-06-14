cmake_minimum_required(VERSION 3.3)
project(Sensor)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

add_library(CPUSensor src/CPUSensor.cpp src/MemorySensor.cpp include/MemorySensor.h src/NetIFSensor.cpp include/NetIFSensor.h src/Worker.cpp include/Worker.h include/Util.h src/DiskSensor.cpp include/DiskSensor.h src/ProcSensor.cpp include/ProcSensor.h src/SystemSensor.cpp include/SystemSensor.h src/UDPClient.cpp include/UDPClient.h src/UDPServer.cpp include/UDPServer.h)

set_target_properties(CPUSensor
        PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/lib"
        )

