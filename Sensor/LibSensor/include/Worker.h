//
// Created by pSolT on 23.04.16.
//

#ifndef SENSOR_WORKER_H
#define SENSOR_WORKER_H

#include "ISensor.h"
#include "Util.h"
#include "UDPClient.h"
#include <iostream>
#include <vector>
#include <thread>
#include <chrono>
#include <memory>

class Worker
{
public:
    Worker();
    Worker& AddSensor(ISensor * sensor);
    void run();
    void SetTargetHost(std::string targetHost);
    std::string GetTargetHost() const;
    void SetTargetPort(std::string targetPort);
    std::string GetTargetPort() const;
    void SetName(std::string name);
    std::string GetName() const;
    void SetDataInterval(unsigned dataInterval);
    unsigned GetDataInterval() const;
    void SetMetadataInterval(unsigned metaDataInterval);
    unsigned GetMedaDataInterval() const;

private:
    std::vector<std::unique_ptr<ISensor>> _sensors;
    unsigned _dataInterval;
    unsigned _metadataInterval;
    std::string _targetHost;
    std::string _targetPort;
    std::string _name;
    UDPClient * _udpClient;

};


#endif //SENSOR_WORKER_H
