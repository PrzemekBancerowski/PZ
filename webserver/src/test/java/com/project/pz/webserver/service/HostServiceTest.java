package com.project.pz.webserver.service;

import com.project.pz.webserver.AbstractTest;
import com.project.pz.webserver.model.response.HostsResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class HostServiceTest extends AbstractTest {

    @Autowired
    private HostService hostService;

    @Test
    public void getHostsForIdTest() throws Exception {
        Integer monitorId = 1;
        HostsResponse response = hostService.getHosts(monitorId);

        assertNotNull(response);
        assertNotNull(response.getHosts());
        assertFalse(response.getHosts().isEmpty());
        checkHosts(response.getHosts());

    }

    public static void checkHosts(Map<String, List<String>> hosts) {
        for (Map.Entry<String, List<String>> entry : hosts.entrySet()) {
            assertNotNull(entry.getKey());
            assertFalse(entry.getKey().isEmpty());
            assertNotNull(entry.getValue());
            assertFalse(entry.getValue().isEmpty());
            for (String string : entry.getValue()) {
                assertNotNull(string);
                assertFalse(string.isEmpty());
            }
        }
    }
}
