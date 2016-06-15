package com.project.pz.webserver.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.project.pz.webserver.AbstractMvcTest;
import com.project.pz.webserver.model.MonitorModel;
import com.project.pz.webserver.service.MonitorService;
import com.project.pz.webserver.service.MonitorServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Transactional
public class MonitorControllerTest extends AbstractMvcTest {

    @InjectMocks
    protected MonitorController monitorController;

    @Autowired
    private MonitorService monitorService;

    @Before
    public void init() {
        ReflectionTestUtils.setField(monitorController, "monitorService", monitorService);
    }

    @Test
    public void getMonitorsTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/monitors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(content().encoding(EXPECTED_ENCODING))
                .andReturn();

        List<MonitorModel> monitorList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<MonitorModel>>() {
        });
        MonitorServiceTest.testMonitorList(monitorList);
    }


    @Override
    protected Object[] getInjectedControllers() {
        return new Object[]{monitorController};
    }
}
