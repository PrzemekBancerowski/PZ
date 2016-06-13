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

}
