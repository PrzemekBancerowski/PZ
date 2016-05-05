//
// Created by pSolT on 23.04.16.
//

#ifndef SENSOR_WORKER_H
#define SENSOR_WORKER_H

#include "ISensor.h"
#include "Util.h"
#include <iostream>
#include <vector>
#include <thread>
#include <memory>

class Worker
{
public:
    Worker();
    Worker& AddSensor(ISensor * sensor);
    Json::Value run() const;

private:
    std::vector<std::unique_ptr<ISensor>> _sensors;

};


#endif //SENSOR_WORKER_H
