//
// Created by pSolT on 23.04.16.
//

#ifndef SENSOR_CPUSENSOR_H
#define SENSOR_CPUSENSOR_H

#include "ISensor.h"
#include <iostream>
#include <chrono>
#include <thread>


extern "C" {
#include <sigar_format.h>
}

#include <thread>
#include <chrono>

class CPUSensor : public ISensor{
public:
    CPUSensor();
    CPUSensor(unsigned interval);
    virtual ~CPUSensor();
    virtual Json::Value GetMetaData() const;
    virtual std::string GetTypeName() const;
    virtual Json::Value GetData() const;

private:
    unsigned _interval;
};


#endif //SENSOR_CPUSENSOR_H
