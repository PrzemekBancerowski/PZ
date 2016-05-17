package com.project.pz.webserver.service;

import com.project.pz.webserver.entity.User;

import java.util.Optional;

/**
 * Created by Piotr Sołtysiak on 2016-05-06.
 * Contact: piotrek.soltysiak@gmail.com
 */
public interface UserService {

    User create(String email, String password);

    Optional<User> getUserById(Integer id);

    Optional<User> getUserByEmail(String email);

}
