package org.pz.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pz.listener.model.SensorDetails;
import org.pz.listener.model.SensorMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Katarzyna Piotrowicz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
public class JsonMapperTest {

    ObjectMapper mapper = new ObjectMapper();
    private static final String JSON_FILE = "response.json";
    @Autowired
    DatabaseController controller;

    @Test
    @Ignore
    public void shouldPringSensorDetails() throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(JSON_FILE);
        InputStream is2 = Thread.currentThread().getContextClassLoader().getResourceAsStream(JSON_FILE);

        SensorMetadata meta = mapper.readValue(is, SensorMetadata.class);
        System.out.println(meta);

        SensorDetails details = mapper.readValue(is2, SensorDetails.class);
        System.out.println(details);

        controller.saveNewSensorDetails(details);
        controller.saveNewSensorMetadata(meta);
    }

}
