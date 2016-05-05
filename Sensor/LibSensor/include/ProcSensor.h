//
// Created by pSolT on 23.04.16.
//

#ifndef SENSOR_PROCSENSOR_H
#define SENSOR_PROCSENSOR_H

#include "ISensor.h"

class ProcSensor: public ISensor {
public:
    ProcSensor();
    virtual ~ProcSensor();
    virtual Json::Value Run() const;
    virtual Json::Value GetMetaData() const;
    virtual std::string GetTypeName() const;

};


#endif //SENSOR_PROCSENSOR_H
