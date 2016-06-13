package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.entity.User;
import com.project.pz.webserver.model.UserModel;
import com.project.pz.webserver.repository.UserRepository;
import com.project.pz.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Piotr Sołtysiak on 2016-05-06.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class UserServiceDefaultImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel create(UserModel model) {
        User savedUser = userRepository.save(new User(model));
        return new UserModel(savedUser);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findOneByEmail(email).get();
    }
}
