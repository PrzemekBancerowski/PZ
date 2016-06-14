//
// Created by pSolT on 23.04.16.
//

#include <sigar.h>
#include <iomanip>
#include <boost/algorithm/hex.hpp>
#include "../include/Worker.h"

Worker::Worker() {
    sigar_t * sigar;
    sigar_open(&sigar);

    sigar_net_interface_config_t netIfCfg;

    sigar_net_interface_config_primary_get(sigar, &netIfCfg);


    std::string res;
    boost::algorithm::hex(netIfCfg.hwaddr.addr.mac, netIfCfg.hwaddr.addr.mac + 6, back_inserter(res));


    _id =res;

    sigar_close(sigar);
}

Worker& Worker::AddSensor(ISensor *sensor)
{
    _sensors.push_back(std::unique_ptr<ISensor>(sensor));
    return *this;
}


Json::Value Worker::RunOnce()
{
    Json::Value json;

    Json::Value data;
    Json::Value metaData;

    data["ID"] = _id;

    for(int i=0;i<_sensors.size();i++)
    {
        data[_sensors[i]->GetTypeName()] = _sensors[i]->GetData();
        metaData[_sensors[i]->GetTypeName()] = _sensors[i]->GetMetaData();
    }

    json["data"] = data;
    json["metadata"] = metaData;

    return json;

}

void Worker::run()
{
    Json::Value json;
    boost::asio::io_service io_service;
    _udpClient = new UDPClient(io_service, _targetHost, _targetPort);
    io_service.run();
    while(true)
    {
        std::chrono::steady_clock::time_point tp = std::chrono::steady_clock::now() + std::chrono::milliseconds(_dataInterval);

        //zbierz dane
        Json::Value json;

        Json::Value data;
        Json::Value metaData;

        data["ID"] = _id;

        for(int i=0;i<_sensors.size();i++)
        {
            data[_sensors[i]->GetTypeName()] = _sensors[i]->GetData();
            metaData[_sensors[i]->GetTypeName()] = _sensors[i]->GetMetaData();
        }

        json["data"] = data;
        json["metadata"] = metaData;




        //czekaj dopóki time stamp
        std::this_thread::sleep_until(tp);
        //wyślij

        _udpClient->Send(json.toStyledString());


    }



}

void Worker::SetTargetHost(std::string targetHost)
{
    _targetHost = targetHost;
}
std::string Worker::GetTargetHost() const
{
    return _targetHost;
}
void Worker::SetTargetPort(std::string targetPort)
{
    _targetPort = targetPort;
}
std::string Worker::GetTargetPort() const
{
    return  _targetPort;
}
void Worker::SetName(std::string name)
{
    _name = name;
}
std::string Worker::GetName() const
{
    return _name;
}
void Worker::SetDataInterval(unsigned dataInterval)
{
    _dataInterval = dataInterval;
}
unsigned Worker::GetDataInterval() const
{
    return _dataInterval;
}
void Worker::SetMetadataInterval(unsigned metadataInterval)
{
    _metadataInterval = metadataInterval;
}
unsigned Worker::GetMedaDataInterval() const
{
    return _metadataInterval;
}
