package com.project.pz.webserver.controller;

import com.project.pz.webserver.AbstractMvcTest;
import com.project.pz.webserver.model.response.HostResponse;
import com.project.pz.webserver.service.HostService;
import com.project.pz.webserver.service.HostServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class HostControllerTest extends AbstractMvcTest {

    @InjectMocks
    protected HostController hostController;

    @Autowired
    private HostService hostService;

    @Before
    public void init() {
        ReflectionTestUtils.setField(hostController, "hostService", hostService);
    }

    @Test
    public void getForMonitorIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/hosts/")
                .param("monitorId", "1")
                .header("token", "aaa"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(content().encoding(EXPECTED_ENCODING))
                .andReturn();

        HostResponse response = mapper.readValue(result.getResponse().getContentAsString(), HostResponse.class);

        assertNotNull(response);
        assertNotNull(response.getHosts());

        HostServiceTest.checkHosts(response.getHosts());
    }

    @Override
    protected Object[] getInjectedControllers() {
        return new Object[]{hostController};
    }
}
