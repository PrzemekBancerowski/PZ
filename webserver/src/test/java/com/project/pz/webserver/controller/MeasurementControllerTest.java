package com.project.pz.webserver.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.project.pz.webserver.AbstractMvcTest;
import com.project.pz.webserver.dict.Measure;
import com.project.pz.webserver.dict.MetricType;
import com.project.pz.webserver.model.MetricModel;
import com.project.pz.webserver.model.MonitorSimpleModel;
import com.project.pz.webserver.model.SensorModel;
import com.project.pz.webserver.service.MeasurementService;
import com.project.pz.webserver.service.MetricService;
import com.project.pz.webserver.service.MonitorService;
import com.project.pz.webserver.service.SensorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class MeasurementControllerTest extends AbstractMvcTest {

    @InjectMocks
    protected MeasurementController measurementController;

    @InjectMocks
    protected MonitorController monitorController;

    @InjectMocks
    protected SensorController sensorController;

    @InjectMocks
    protected MetricsController metricsController;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private MetricService metricService;

    @Before
    public void init() {
        ReflectionTestUtils.setField(measurementController, "measurementService", measurementService);
        ReflectionTestUtils.setField(monitorController, "monitorService", monitorService);
        ReflectionTestUtils.setField(sensorController, "sensorService", sensorService);
        ReflectionTestUtils.setField(metricsController, "metricService", metricService);
    }

    @Test
    public void getMeasurementsTest() throws Exception {
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

        MvcResult getMeasurementResult = mockMvc.perform(get("/monitors/" + monitorId + "/sensors/" + sensorId + "/metrics/" + addedMetric.getId()+ "/measurements"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(content().encoding(EXPECTED_ENCODING))
                .andReturn();

        List<BigDecimal> measureList = mapper.readValue(getMeasurementResult.getResponse().getContentAsString(), new TypeReference<List<BigDecimal>>() {
        });

        assertNotNull(measureList);
        assertFalse(measureList.isEmpty());

    }

    @Override
    protected Object[] getInjectedControllers() {
        return new Object[]{measurementController, monitorController, sensorController, metricsController};
    }
}
