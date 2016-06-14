//
// Created by pSolT on 23.04.16.
//
extern "C" {
#include <sigar.h>
#include <sigar_format.h>
}
#include "../include/CPUSensor.h"

CPUSensor::CPUSensor()
{

}

CPUSensor::CPUSensor(unsigned interval)
{
    _interval = interval;
}

CPUSensor::~CPUSensor()
{

}

std::string CPUSensor::GetTypeName() const
{
    std::string typeName = "CPU";
    return  typeName;
}

Json::Value CPUSensor::GetMetaData() const
{
    Json::Value metaData;
    sigar_t *sigar_cpu;
    sigar_cpu_info_list_t cpuInfoList;

    sigar_open(&sigar_cpu);


    sigar_cpu_info_list_get(sigar_cpu, &cpuInfoList);

    metaData["vendor"] = cpuInfoList.data[0].vendor;
    metaData["model"] = cpuInfoList.data[0].model;
    metaData["total_cores"] = cpuInfoList.data[0].total_cores;
    int cacheSize = cpuInfoList.data[0].cache_size;
    metaData["cache_size"] = cacheSize;
    metaData["mhz_min"] = cpuInfoList.data[0].mhz_min;
    metaData["mhz_max"] = cpuInfoList.data[0].mhz_max;


    sigar_close(sigar_cpu);

    return metaData;
}

Json::Value CPUSensor::GetData() const
{

    Json::Value root;


    sigar_t *sigar_cpu;
    sigar_cpu_t old;
    sigar_cpu_t current;

    sigar_open(&sigar_cpu);
    sigar_cpu_get(sigar_cpu, &old);

    sigar_cpu_perc_t perc;

    std::this_thread::sleep_for(std::chrono::milliseconds(100));

    sigar_cpu_get(sigar_cpu, &current);
    sigar_cpu_perc_calculate(&old, &current, &perc);
    root["Usage"] = (float)perc.combined * 100;

//    parameters["Cache"] = 2;


    sigar_close(sigar_cpu);

    return root;
}
