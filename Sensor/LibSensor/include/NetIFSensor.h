//
// Created by pSolT on 23.04.16.
//

#ifndef SENSOR_NETIFSENSOR_H
#define SENSOR_NETIFSENSOR_H

#include "ISensor.h"

class NetIFSensor : public ISensor{
public:
    NetIFSensor();
    virtual ~NetIFSensor();
    virtual Json::Value GetMetaData() const;
    virtual std::string GetTypeName() const;
    virtual Json::Value GetData() const;
};


#endif //SENSOR_NETIFSENSOR_H
