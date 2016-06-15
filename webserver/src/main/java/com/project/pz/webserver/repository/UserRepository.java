package com.project.pz.webserver.repository;

import com.project.pz.webserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findOneByEmail(String email);

}
