package com.project.pz.webserver.service;

import com.project.pz.webserver.AbstractTest;
import com.project.pz.webserver.model.MonitorModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
        List<MonitorModel> monitorList = monitorService.getMonitors();
        testMonitorList(monitorList);
    }

    public static void testMonitorList(List<MonitorModel> monitorList) {
        assertNotNull(monitorList);
        assertTrue(monitorList.size() > 0);

        for (MonitorModel monitor : monitorList) {
            assertNotNull(monitor.getId());
            assertNotNull(monitor.getAddress());
            assertNotNull(monitor.getName());
            assertFalse(monitor.getAddress().isEmpty());
            assertFalse(monitor.getName().isEmpty());
        }
    }

}
