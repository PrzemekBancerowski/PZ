//
// Created by pSolT on 23.04.16.
//

#include "../include/Worker.h"

Worker::Worker() {

}

Worker& Worker::AddSensor(ISensor *sensor)
{
    _sensors.push_back(std::unique_ptr<ISensor>(sensor));
    return *this;
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
        for(int i=0;i<_sensors.size();i++)
        {
            json[_sensors[i]->GetTypeName()] = _sensors[i]->Run();
        }



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
