package com.project.pz.webserver.service.impl;

import com.google.common.collect.Lists;
import com.project.pz.webserver.model.SensorModel;
import com.project.pz.webserver.service.SensorService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class SensorServiceDefaultImpl implements SensorService {

    @Override
    public List<SensorModel> getSensors(String monitorId) {
        return mock();
    }

    private List<SensorModel> mock() {
        List<SensorModel> sensorModelList = Lists.newArrayList();

        for (int i = 1; i<6; i++) {
            SensorModel sensorModel = new SensorModel();
            sensorModel.setId("Id_"+i);
            sensorModel.setHostName("HostName_"+i);
            sensorModel.setCpu("Cpu_ID_"+i);
            sensorModel.setCpuVendor("CpuVendor_"+i);
            sensorModel.setCpuCoreCount(i);
            sensorModel.setMemorySize(BigInteger.valueOf(i).multiply(BigInteger.valueOf(1073741824)));
            sensorModel.setSystemName("SystemName_"+i);
            sensorModel.setSystemArch("SystemArch_"+i);
            sensorModel.setSystemVendor("SystemVendor_"+i);
            sensorModel.setSystemVersion("SystemVersion_"+i);

            sensorModelList.add(sensorModel);
        }


        return sensorModelList;
    }
}
