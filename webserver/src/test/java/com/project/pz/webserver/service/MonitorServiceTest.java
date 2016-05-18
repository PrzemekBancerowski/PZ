package com.project.pz.webserver.service;

import com.project.pz.webserver.AbstractTest;
import com.project.pz.webserver.model.MonitorSimpleModel;
import com.project.pz.webserver.model.MonitorDetailModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class MonitorServiceTest extends AbstractTest {

    @Autowired
    private MonitorService monitorService;

    @Test
    public void getMonitorsTest() throws Exception {
        List<MonitorSimpleModel> monitorList = monitorService.getMonitors();
        testMonitorList(monitorList);
    }

    public static void testMonitorList(List<MonitorSimpleModel> monitorList) {
        assertNotNull(monitorList);
        assertTrue(monitorList.size() > 0);

        for (MonitorSimpleModel monitor : monitorList) {
            assertNotNull(monitor.getId());
            assertNotNull(monitor.getAddress());
            assertNotNull(monitor.getName());
            assertFalse(monitor.getAddress().isEmpty());
            assertFalse(monitor.getName().isEmpty());
        }
    }

    @Test
    public void getMonitorForIdTest() throws Exception {
        MonitorDetailModel monitor = monitorService.getMonitorForId(1);
        testMonitorDetailModel(monitor);
    }

    public static void testMonitorDetailModel(MonitorDetailModel monitor) {
        assertNotNull(monitor);
        assertNotNull(monitor.getId());
        assertNotNull(monitor.getName());
        assertFalse(monitor.getName().isEmpty());
        assertNotNull(monitor.getAddress());
        assertFalse(monitor.getAddress().isEmpty());

        Map<String, List<String>> hosts = monitor.getHosts();

        assertNotNull(hosts);
        assertFalse(hosts.isEmpty());

        HostServiceTest.checkHosts(hosts);
    }

    @Test
    public void getMonitorForSensorId() throws Exception {
        MonitorDetailModel monitor = monitorService.getMonitorForId(1);

        MonitorSimpleModel monitorSimpleModel = new MonitorSimpleModel();
        monitorSimpleModel.setId(monitor.getId());
        monitorSimpleModel.setName(monitor.getName());
        monitorSimpleModel.setAddress(monitor.getAddress());

        String sensorId = monitor.getHosts().entrySet().iterator().next().getValue().get(1);

        MonitorSimpleModel foundMonitorSimpleModel = monitorService.getMonitorForSensorId(sensorId);

        assertNotNull(foundMonitorSimpleModel);
        assertEquals(monitorSimpleModel, foundMonitorSimpleModel);
    }

}
