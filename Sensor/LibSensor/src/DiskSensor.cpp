//
// Created by pSolT on 23.04.16.
//

#include <sigar.h>
#include "../include/DiskSensor.h"

DiskSensor::DiskSensor()
{

}

DiskSensor::~DiskSensor()
{

}

std::string DiskSensor::GetTypeName() const
{
    std::string typeName = "Disk";
    return  typeName;
}



Json::Value DiskSensor::GetMetaData() const
{
    Json::Value metaData;

    sigar_t *fileSystem;


    sigar_open(&fileSystem);

    sigar_file_system_list_t fsList;

    sigar_file_system_list_get(fileSystem, &fsList);

    std::set<std::string> devices;

    for(int i=0;i<fsList.number;i++)
    {
        if(boost::starts_with(fsList.data[i].dev_name, "/dev/sd") || boost::starts_with(fsList.data[i].dev_name, "/dev/hd"))
        {

            devices.insert(fsList.data[i].dev_name);

        }
    }
    sigar_close(fileSystem);




    for(auto it=devices.begin();it!= devices.end();it++)
    {

        std::cout << *it << " " << std::endl;
    }





    return metaData;
}

Json::Value DiskSensor::GetData() const
{


}
