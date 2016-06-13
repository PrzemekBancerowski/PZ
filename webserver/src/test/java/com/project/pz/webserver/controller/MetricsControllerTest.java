package com.project.pz.webserver.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.project.pz.webserver.AbstractMvcTest;
import com.project.pz.webserver.dict.Measure;
import com.project.pz.webserver.dict.MetricType;
import com.project.pz.webserver.model.MetricModel;
import com.project.pz.webserver.model.MonitorSimpleModel;
import com.project.pz.webserver.model.SensorModel;
import com.project.pz.webserver.service.MetricService;
import com.project.pz.webserver.service.MonitorService;
import com.project.pz.webserver.service.SensorService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.NestedServletException;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Piotr Sołtysiak on 2016-06-13.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class MetricsControllerTest extends AbstractMvcTest {

    @InjectMocks
    private MetricsController metricsController;

    @InjectMocks
    private MonitorController monitorController;

    @InjectMocks
    private SensorController sensorController;

    @Autowired
    private MetricService metricService;

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private SensorService sensorService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        ReflectionTestUtils.setField(sensorController, "sensorService", sensorService);
        ReflectionTestUtils.setField(monitorController, "monitorService", monitorService);
        ReflectionTestUtils.setField(metricsController, "metricService", metricService);
    }

    @Test
    public void getMetricsTest() throws Exception {
        MvcResult getMonitorsResult = mockMvc.perform(get("/monitors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(content().encoding(EXPECTED_ENCODING))
                .andReturn();

        List<MonitorSimpleModel> monitorList = mapper.readValue(getMonitorsResult.getResponse().getContentAsString(), new TypeReference<List<MonitorSimpleModel>>() {
        });

        for (MonitorSimpleModel monitor : monitorList) {
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
                    MvcResult getMetricResult = mockMvc.perform(get("/monitors/" + monitor.getId() + "/sensors/" + sensor.getId() + "/metrics"))
                            .andExpect(status().isOk())
                            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                            .andDo(print())
                            .andExpect(content().encoding(EXPECTED_ENCODING))
                            .andReturn();

                    List<MetricModel> metricList = mapper.readValue(getMetricResult.getResponse().getContentAsString(), new TypeReference<List<MetricModel>>() {
                    });

                    if (metricList != null) {
                        for (MetricModel metric : metricList) {
                            assertNotNull(metric.getId());
                            assertNotNull(metric.getDescription());
                            assertNotNull(metric.getMetricType());
                            assertNotNull(metric.getMeasure());
                            assertNotNull(metric.getUserId());
                            assertNotNull(metric.getInterval());
                            assertNotNull(metric.getWindowSize());
                        }
                    }

                }
            }
        }
    }

    @Test
    public void addGetAndDeleteMetricTest() throws Exception {
        String monitorId = null;
        String sensorId = null;

        MvcResult getMonitorsResult = mockMvc.perform(get("/monitors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(content().encoding(EXPECTED_ENCODING))
                .andReturn();

        List<MonitorSimpleModel> monitorList = mapper.readValue(getMonitorsResult.getResponse().getContentAsString(), new TypeReference<List<MonitorSimpleModel>>() {
        });

        for (MonitorSimpleModel monitor : monitorList) {
            MvcResult getSensorsResult = mockMvc.perform(get("/monitors/" + monitor.getId() + "/sensors"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andDo(print())
                    .andExpect(content().encoding(EXPECTED_ENCODING))
                    .andReturn();

            List<SensorModel> sensorList = mapper.readValue(getSensorsResult.getResponse().getContentAsString(), new TypeReference<List<SensorModel>>() {
            });

            if (sensorList != null && !sensorList.isEmpty()) {
                monitorId = monitor.getId();
                sensorId = sensorList.get(0).getId();
                break;
            }
        }

        // ADD

        MetricModel metricModel = new MetricModel();
        metricModel.setDescription("MetricDescription");
        metricModel.setInterval(10);
        metricModel.setMeasure(Measure.CPU);
        metricModel.setMetricType(MetricType.SIMPLE);
        metricModel.setWindowSize(100);
        metricModel.setUserId("TEST_USER");

        MvcResult addMetricResult = mockMvc.perform(post("/monitors/" + monitorId + "/sensors/" + sensorId + "/metrics")
                .content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(metricModel))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(content().encoding(EXPECTED_ENCODING))
                .andReturn();

        MetricModel addedMetric = mapper.readValue(addMetricResult.getResponse().getContentAsString(), MetricModel.class);

        assertNotNull(addedMetric);
        assertNotNull(addedMetric.getId());
        assertEquals(metricModel.getDescription(), addedMetric.getDescription());
        assertEquals(metricModel.getInterval(), addedMetric.getInterval());
        assertEquals(metricModel.getMeasure(), addedMetric.getMeasure());
        assertEquals(metricModel.getMetricType(), addedMetric.getMetricType());
        assertEquals(metricModel.getWindowSize(), addedMetric.getWindowSize());
        assertEquals(metricModel.getUserId(), addedMetric.getUserId());

        // GET

        MvcResult getMetricResult = mockMvc.perform(get("/monitors/" + monitorId + "/sensors/" + sensorId + "/metrics/" + addedMetric.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(content().encoding(EXPECTED_ENCODING))
                .andReturn();

        MetricModel obtainedMetric = mapper.readValue(getMetricResult.getResponse().getContentAsString(), MetricModel.class);

        assertNotNull(obtainedMetric);
        assertEquals(obtainedMetric.getId(), addedMetric.getId());
        assertEquals(obtainedMetric.getDescription(), addedMetric.getDescription());
        assertEquals(obtainedMetric.getInterval(), addedMetric.getInterval());
        assertEquals(obtainedMetric.getMeasure(), addedMetric.getMeasure());
        assertEquals(obtainedMetric.getMetricType(), addedMetric.getMetricType());
        assertEquals(obtainedMetric.getWindowSize(), addedMetric.getWindowSize());
        assertEquals(obtainedMetric.getUserId(), addedMetric.getUserId());

        // DELETE

        mockMvc.perform(delete("/monitors/" + monitorId + "/sensors/" + sensorId + "/metrics/" + addedMetric.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // DELETE TEST

        exception.expect(NestedServletException.class);

        mockMvc.perform(get("/monitors/" + monitorId + "/sensors/" + sensorId + "/metrics/" + addedMetric.getId()))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn();

    }


    @Override
    protected Object[] getInjectedControllers() {
        return new Object[]{metricsController, monitorController, sensorController};
    }
}
