//
// Created by pSolT on 23.04.16.
//

#include <sigar.h>
#include "../include/SystemSensor.h"

SystemSensor::SystemSensor()
{

}

SystemSensor::~SystemSensor()
{

}

std::string SystemSensor::GetTypeName() const
{
    std::string typeName = "System";
    return  typeName;
}

Json::Value SystemSensor::GetMetaData() const
{
    Json::Value metaData;

    sigar_t * sigar;

    sigar_sys_info_t sysInfo;
    sigar_open(&sigar);
    sigar_sys_info_get(sigar, &sysInfo);

    metaData["vendor"] = sysInfo.vendor;
    metaData["name"] = sysInfo.name;
    metaData["arch"] = sysInfo.arch;
    metaData["version"] = sysInfo.version;

    sigar_close(sigar);

    return metaData;
}

Json::Value SystemSensor::GetData() const
{


}