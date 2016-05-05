//
// Created by pSolT on 23.04.16.
//

#ifndef SENSOR_UTIL_H
#define SENSOR_UTIL_H

#include "ISensor.h"
#include <type_traits>

template<typename T>
struct IsSensorType
{
    static const bool value = std::is_base_of<ISensor,T>::value;
};



#endif //SENSOR_UTIL_H
