//
// Created by pSolT on 23.04.16.
//

#include <sigar.h>
#include "../include/ProcSensor.h"

ProcSensor::ProcSensor()
{

}

ProcSensor::~ProcSensor()
{

}

std::string ProcSensor::GetTypeName() const
{
    std::string typeName = "Proc";
    return  typeName;
}

Json::Value ProcSensor::GetMetaData() const
{

}

Json::Value ProcSensor::GetData() const
{
    Json::Value data;

    sigar_t * sigar;
    sigar_open(&sigar);

    sigar_proc_stat_t procStat;

    sigar_proc_stat_get(sigar, &procStat);

    data["idle"] = (Json::Value::UInt64)procStat.idle;
    data["running"] = (Json::Value::UInt64)procStat.running;
    data["sleeping"] =(Json::Value::UInt64) procStat.sleeping;
    data["stopped"] = (Json::Value::UInt64)procStat.stopped;
    data["total"] = (Json::Value::UInt64)procStat.total;
    data["threads"] = (Json::Value::UInt64)procStat.threads;
    data["zombie"] = (Json::Value::UInt64)procStat.zombie;

    sigar_close(sigar);
    return data;
}