//
// Created by pSolT on 23.04.16.
//

#ifndef SENSOR_ISENSOR_H
#define SENSOR_ISENSOR_H

#include<iostream>

#include <json/json.h>
#include <sigar.h>


class ISensor {
public:
    virtual Json::Value GetData() const = 0;
    virtual Json::Value GetMetaData() const = 0;
    virtual std::string GetTypeName() const = 0;
    virtual ~ISensor() {}
};


#endif //SENSOR_ISENSOR_H
