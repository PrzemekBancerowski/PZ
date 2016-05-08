//
// Created by pSolT on 23.04.16.
//

#include <sigar.h>
#include "../include/NetIFSensor.h"

NetIFSensor::NetIFSensor()
{

}

NetIFSensor::~NetIFSensor()
{

}

std::string NetIFSensor::GetTypeName() const
{
    std::string typeName = "NetIF";
    return  typeName;
}

Json::Value NetIFSensor::GetMetaData() const
{
    Json::Value metaData;

    sigar_t * sigarNet;
    sigar_net_info_t current;
    sigar_open(&sigarNet);
    sigar_net_info_get(sigarNet, &current);

    metaData["domain_name"] = current.domain_name;
    metaData["host_name"] = current.host_name;
    metaData["default_gateway"] = current.default_gateway;
    metaData["default_gateway_interface"] = current.default_gateway_interface;
    metaData["primary_dns"] = current.primary_dns;
    metaData["secondary_dns"] = current.secondary_dns;

    sigar_close(sigarNet);

    return metaData;

}

Json::Value NetIFSensor::GetData() const
{
    Json::Value data;
    sigar_t * sigarNet;
    sigar_open(&sigarNet);

    sigar_net_stat_t netStat;

    sigar_net_stat_get(sigarNet, &netStat,0);

    sigar_net_interface_stat_t stat;
    sigar_net_interface_list_t ifList;

    sigar_net_interface_list_get(sigarNet, &ifList);

    for(int i=0;i<ifList.number;i++)
    {
        std::cout << ifList.data[i] << std::endl;
        Json::Value parameters;

        sigar_net_interface_stat_get(sigarNet,ifList.data[i], &stat);

        parameters["speed"] = (Json::Value::UInt64)stat.speed;

        parameters["rx_dropped"] = (Json::Value::UInt64)stat.rx_dropped;
        parameters["rx_packets"] = (Json::Value::UInt64)stat.rx_packets;
        parameters["rx_bytes"] = (Json::Value::UInt64)stat.rx_bytes;
        parameters["rx_overruns"] = (Json::Value::UInt64)stat.rx_overruns;
        parameters["tx_packets"] = (Json::Value::UInt64)stat.tx_packets;
        parameters["tx_bytes"] = (Json::Value::UInt64)stat.tx_bytes;
        parameters["tx_dropped"] = (Json::Value::UInt64)stat.tx_dropped;
        parameters["tx_collisions"] = (Json::Value::UInt64)stat.tx_collisions;


        data[ifList.data[i]] = parameters;

    }


    sigar_close(sigarNet);
    return data;

}

