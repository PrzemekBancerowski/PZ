//
// Created by pSolT on 23.04.16.
//

#ifndef SENSOR_DISKSENSOR_H
#define SENSOR_DISKSENSOR_H

#include "ISensor.h"
#include <set>
#include <boost/algorithm/string/predicate.hpp>

class DiskSensor : public ISensor {
public:
    DiskSensor();
    virtual Json::Value GetData() const;
    virtual Json::Value GetMetaData() const;
    virtual std::string GetTypeName() const;
    virtual ~DiskSensor();

};


#endif //SENSOR_DISKSENSOR_H
