//
// Created by pSolT on 23.04.16.
//

#include <sigar.h>
#include "../include/MemorySensor.h"

MemorySensor::MemorySensor()
{

}

MemorySensor::~MemorySensor()
{

}

std::string MemorySensor::GetTypeName() const
{
    std::string typeName = "Memory";
    return  typeName;
}

Json::Value MemorySensor::GetMetaData() const
{
    Json::Value metaData;

    sigar_t *sigar_mem;
    sigar_mem_t current;
    sigar_open(&sigar_mem);
    sigar_mem_get(sigar_mem, &current);

    metaData["size"] = (long long int)current.total;

    sigar_close(sigar_mem);

    return metaData;
}


Json::Value MemorySensor::GetData() const
{
    Json::Value data;

    sigar_t *sigar_mem;
    sigar_mem_t current;
    sigar_open(&sigar_mem);
    sigar_mem_get(sigar_mem, &current);


    data["usage"] = current.used_percent;
    sigar_close(sigar_mem);
    return data;
}