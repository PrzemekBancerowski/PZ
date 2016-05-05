#include <iostream>
#include <chrono>
#include <thread>
#include "json/json.h"
#include "yaml-cpp/yaml.h"
#include <sigar.h>
#include "LibSensor/include/CPUSensor.h"
#include "LibSensor/include/NetIFSensor.h"
#include "LibSensor/include/MemorySensor.h"
#include "LibSensor/include/DiskSensor.h"
#include "LibSensor/include/Worker.h"
#include "LibSensor/include/Util.h"


using namespace std;

int main()
{

    ISensor * memorySensor = new MemorySensor();
    ISensor * cpuSensor = new CPUSensor();
    ISensor * netIFSensor = new NetIFSensor();
    ISensor * diskSensor = new DiskSensor();

    std::cout << diskSensor->GetMetaData().toStyledString() << std::endl;

//
//    Worker worker;
//    worker.AddSensor(memorySensor).AddSensor(cpuSensor).AddSensor(netIFSensor);
//
//    std::cout <<  worker.run().toStyledString() << std::endl;




//    YAML::Node config = YAML::LoadFile("config.yaml");
//    YAML::Node configuration = config["configuration"];
//    YAML::Node components = config["components"];
//
//    const std::string name = configuration["name"].as<std::string>();
//    const std::string ip = configuration["ip"].as<std::string>();
//    const std::string port = configuration["port"].as<std::string>();
//    const std::string data_interval = configuration["data_interval"].as<std::string>();
//    const std::string metadata_interval = configuration["metadata_interval"].as<std::string>();
//
//
//    bool isCPUused = components["cpu"].as<bool>();
//    bool isMemoryUsed = components["memory"].as<bool>();
//    bool isNetIFUsed = components["netIF"].as<bool>();
//
//    std::cout   << "NAME: "                 << name     << std::endl
//                << "IP: "                   << ip       << std::endl
//                << "port: "                 << port     << std::endl
//                << "data_interval: "        << data_interval << std::endl
//                << "metadata_interval: "    << metadata_interval << std::endl
//                << "Is cpu used: "          << isCPUused     << std::endl
//                << "Is mem used: "          << isMemoryUsed << std::endl
//                << "Is netIF used: "        << isNetIFUsed << std::endl;

    return 0;

}