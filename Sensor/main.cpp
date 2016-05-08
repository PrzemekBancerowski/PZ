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
#include "LibSensor/include/ProcSensor.h"
#include "LibSensor/include/SystemSensor.h"
#include "LibSensor/include/Worker.h"
#include "LibSensor/include/UDPServer.h"
#include "LibSensor/include/Util.h"


using namespace std;

int main()
{

    YAML::Node config = YAML::LoadFile("config.yaml");
    YAML::Node configuration = config["configuration"];
    YAML::Node components = config["components"];
    YAML::Node other = config["other"];

    const std::string name = configuration["name"].as<std::string>();
    const std::string host = configuration["host"].as<std::string>();
    const std::string port = configuration["port"].as<std::string>();
    const std::string data_interval = configuration["data_interval"].as<std::string>();
    const std::string metadata_interval = configuration["metadata_interval"].as<std::string>();


    bool isCPUused = components["cpu"].as<bool>();
    bool isMemoryUsed = components["memory"].as<bool>();
    bool isNetIFUsed = components["netIF"].as<bool>();
    bool isProcUsed = components["proc"].as<bool>();
    bool isSystemUsed = components["system"].as<bool>();

    bool isDebugMode = other["debug"].as<bool>();

    std::cout   << "NAME: "                 << name     << std::endl
                << "IP: "                   << host       << std::endl
                << "port: "                 << port     << std::endl
                << "data_interval: "        << data_interval << std::endl
                << "metadata_interval: "    << metadata_interval << std::endl
                << "Is cpu used: "          << isCPUused     << std::endl
                << "Is mem used: "          << isMemoryUsed << std::endl
                << "Is netIF used: "        << isNetIFUsed << std::endl;



    Worker worker;
    if(isCPUused)
    {
        ISensor * cpuSensor = new CPUSensor();
        worker.AddSensor(cpuSensor);
    }
    if(isMemoryUsed)
    {
        ISensor * memorySensor = new MemorySensor();
        worker.AddSensor(memorySensor);
    }
    if(isNetIFUsed)
    {
        ISensor * netIFSensor = new NetIFSensor();
        worker.AddSensor(netIFSensor);
    }
    if(isProcUsed)
    {
        ISensor * procSensor = new ProcSensor();
        worker.AddSensor(procSensor);
    }
    if(isSystemUsed)
    {
        ISensor * systemSensor = new SystemSensor();
        worker.AddSensor(systemSensor);
    }

    worker.SetDataInterval(std::stoi(data_interval));
    worker.SetMetadataInterval(std::stoi(metadata_interval));
    worker.SetName(name);
    worker.SetTargetHost(host);
    worker.SetTargetPort(port);

    std::thread workerThread(&Worker::run, std::ref(worker));

    workerThread.detach();
    if(isDebugMode)
    {
        try
        {
            boost::asio::io_service io_service;
            UDPServer server(io_service, 9999);
            io_service.run();
        }
        catch (std::exception& e)
        {
            std::cerr << e.what() << std::endl;
        }

    }


//    ISensor * procSensor = new ProcSensor();
//    worker.AddSensor(procSensor);
//    ISensor * systemSensor = new SystemSensor();
//    worker.AddSensor(systemSensor);
//
//    std::cout << worker.RunOnce().toStyledString() << std::endl;


    return 0;

}