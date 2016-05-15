package org.pz.listener;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Katarzyna Piotrowicz
 */
@Transactional
public interface TestRepository extends CrudRepository<Test, Integer> {

    Page<Test> findAll(Pageable pageable);

    Test findByName(String name);

}
