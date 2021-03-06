package org.pz.listener.core;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.pz.listener.core.dao.SensorDetailsDao;
import org.pz.listener.core.dao.SensorMetadataDao;
import org.pz.listener.core.model.SensorDetails;
import org.pz.listener.core.model.SensorMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Katarzyna Piotrowicz
 */
@Controller
public class DatabaseController {

    @Autowired
    private SensorDetailsDao sensorDetailsDao;
    @Autowired
    private SensorMetadataDao sensorMetadataDao;

    public void saveNewSensorDetails(SensorDetails details) {
        sensorDetailsDao.save(details);
    }

    public void saveNewSensorMetadata(SensorMetadata meta) {
        sensorMetadataDao.save(meta);
    }

    public List<SensorDetails> getAllSensorDetails() {
        return StreamSupport.stream(
                sensorDetailsDao
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<SensorMetadata> getAllSensorMetadata() {
        return StreamSupport.stream(
                sensorMetadataDao
                .findAll()
                .spliterator(), false)
                .collect(Collectors.toList());
    }
}
