package org.pz.listener.core.dao;

import javax.transaction.Transactional;
import org.pz.listener.core.model.SensorDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Katarzyna Piotrowicz
 */
@Transactional
public interface SensorDetailsDao extends CrudRepository<SensorDetails, Integer> {

    Page<SensorDetails> findAll(Pageable pageable);


}
