package org.pz.listener.dao;

import org.pz.listener.model.Test;
import javax.transaction.Transactional;
import org.pz.listener.model.SensorDetails;
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
