//
// Created by pSolT on 23.04.16.
//

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

}

Json::Value NetIFSensor::Run() const
{
    Json::Value root;

    Json::Value parameters;

    parameters["Type"] = "wlan";

    parameters["Cache"] = 2;

    root["parameters"] = parameters;
    root["Name"] = "NET";

    return root;

}

