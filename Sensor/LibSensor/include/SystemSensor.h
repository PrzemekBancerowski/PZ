//
// Created by pSolT on 23.04.16.
//

#ifndef SENSOR_SYSTEMSENSOR_H
#define SENSOR_SYSTEMSENSOR_H

#include "ISensor.h"

class SystemSensor : public ISensor{
public:
    SystemSensor();
    virtual ~SystemSensor();
    virtual Json::Value GetData() const;
    virtual Json::Value GetMetaData() const;
    virtual std::string GetTypeName() const;
};


#endif //SENSOR_SYSTEMSENSOR_H
