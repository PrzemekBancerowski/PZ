package org.pz.listener.core.dao;

import javax.transaction.Transactional;
import org.pz.listener.core.model.SensorMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Katarzyna Piotrowicz
 */
@Transactional
public interface SensorMetadataDao extends CrudRepository<SensorMetadata, Integer> {

    Page<SensorMetadata> findAll(Pageable pageable);
    
}
