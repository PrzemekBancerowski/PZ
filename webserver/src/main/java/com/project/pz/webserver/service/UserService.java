package com.project.pz.webserver.service;

import com.project.pz.webserver.entity.User;
import com.project.pz.webserver.model.UserModel;

/**
 * Created by Piotr Sołtysiak on 2016-05-06.
 * Contact: piotrek.soltysiak@gmail.com
 */
public interface UserService {

    UserModel create(UserModel model);

    User getUserById(Integer id);

    User getUserByEmail(String email);

}
