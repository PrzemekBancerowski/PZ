package org.pz.listener.dao;

import org.pz.listener.model.Test;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Katarzyna Piotrowicz
 */
@Transactional
public interface TestDao extends CrudRepository<Test, Integer> {

    Page<Test> findAll(Pageable pageable);

    Test findByName(String name);

}
