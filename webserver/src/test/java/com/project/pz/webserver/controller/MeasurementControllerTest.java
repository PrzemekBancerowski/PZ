package com.project.pz.webserver.controller;

import com.project.pz.webserver.AbstractMvcTest;
import com.project.pz.webserver.dict.Measure;
import com.project.pz.webserver.model.response.SimpleSensorDetailsResponse;
import com.project.pz.webserver.service.MeasurementService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Autowired
    private MeasurementService measurementService;

    @Before
    public void init() {
        ReflectionTestUtils.setField(measurementController, "measurementService", measurementService);
    }

    //SensorId=sensor6&Measurement=CPU&MaxCount=100&StartTime=0&EndTime=100000000000
    @Test
    public void getSimpleMeasurementTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/measurements/simple")
                .param("SensorId", "sensor6")
                .param("Measurement", Measure.CPU.name())
                .param("MaxCount", "100")
                .param("StartTime", "0")
                .param("EndTime", "100000000000")
                .header("token", "aaa"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(content().encoding(EXPECTED_ENCODING))
                .andReturn();

        SimpleSensorDetailsResponse response = mapper.readValue(result.getResponse().getContentAsString(), SimpleSensorDetailsResponse.class);

        assertNotNull(response);
        assertNotNull(response.getValues());
        assertFalse(response.getValues().isEmpty());

    }

    @Override
    protected Object[] getInjectedControllers() {
        return new Object[]{measurementController};
    }
}
