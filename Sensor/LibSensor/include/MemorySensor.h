//
// Created by pSolT on 23.04.16.
//

#ifndef SENSOR_MEMORYSENSOR_H
#define SENSOR_MEMORYSENSOR_H

#include "ISensor.h"

class MemorySensor : public ISensor {
public:
    MemorySensor();
    virtual ~MemorySensor();
    virtual Json::Value GetMetaData() const;
    virtual std::string GetTypeName() const;
    virtual Json::Value Run() const;
};


#endif //SENSOR_MEMORYSENSOR_H
