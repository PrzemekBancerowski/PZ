package com.project.pz.webserver.repository;

import com.project.pz.webserver.entity.UserMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pi10521 on 2016-05-18.
 */
@Repository
public interface UserMeasurementRepository extends JpaRepository<UserMeasurement, Integer> {
}
