package com.project.pz.webserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import javax.servlet.Filter;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public abstract class AbstractMvcTest {

    protected static final String EXPECTED_ENCODING = "UTF-8";

    @Mock
    protected View mockView;

    protected MockMvc mockMvc;

    protected ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(getInjectedControllers())
                .setSingleView(mockView)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    protected abstract Object[] getInjectedControllers();
}
