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

Json::Value Worker::run() const
{
    Json::Value root;

    for(int i=0;i<_sensors.size();i++)
    {
        root[_sensors[i]->GetTypeName()] = _sensors[i]->Run();
    }
    return  root;
}