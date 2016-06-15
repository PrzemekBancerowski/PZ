package com.project.pz.webserver.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.project.pz.webserver.AbstractMvcTest;
import com.project.pz.webserver.model.MonitorModel;
import com.project.pz.webserver.model.SensorModel;
import com.project.pz.webserver.service.MonitorService;
import com.project.pz.webserver.service.SensorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Piotr Sołtysiak on 2016-06-13.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class SensorControllerTest extends AbstractMvcTest {

    @InjectMocks
    private SensorController sensorController;

    @InjectMocks
    private MonitorController monitorController;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private MonitorService monitorService;

    @Before
    public void init() {
        ReflectionTestUtils.setField(sensorController, "sensorService", sensorService);
        ReflectionTestUtils.setField(monitorController, "monitorService", monitorService);
    }

    @Test
    public void getSensorsTest() throws Exception {
        MvcResult getMonitorsResult = mockMvc.perform(get("/monitors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(content().encoding(EXPECTED_ENCODING))
                .andReturn();

        List<MonitorModel> monitorList = mapper.readValue(getMonitorsResult.getResponse().getContentAsString(), new TypeReference<List<MonitorModel>>() {
        });

        for (MonitorModel monitor : monitorList) {
            MvcResult getSensorsResult = mockMvc.perform(get("/monitors/" + monitor.getId() + "/sensors"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(print())
                    .andExpect(content().encoding(EXPECTED_ENCODING))
                    .andReturn();

            List<SensorModel> sensorList = mapper.readValue(getSensorsResult.getResponse().getContentAsString(), new TypeReference<List<SensorModel>>() {
            });

            if (sensorList != null) {
                for (SensorModel sensor : sensorList) {
                    assertNotNull(sensor.getId());
                    assertNotNull(sensor.getHostName());
                    assertNotNull(sensor.getCpu());
                    assertNotNull(sensor.getCpuCoreCount());
                    assertNotNull(sensor.getMemorySize());
                    assertNotNull(sensor.getCpuVendor());
                    assertNotNull(sensor.getSystemName());
                    assertNotNull(sensor.getSystemArch());
                    assertNotNull(sensor.getSystemVendor());
                    assertNotNull(sensor.getSystemVersion());

                }
            }
        }
    }

    @Override
    protected Object[] getInjectedControllers() {
        return new Object[]{sensorController, monitorController};
    }
}
