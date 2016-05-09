package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.dict.Role;
import com.project.pz.webserver.entity.User;
import com.project.pz.webserver.repository.UserRepository;
import com.project.pz.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Piotr Sołtysiak on 2016-05-06.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class UserServiceDefaultImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(new BCryptPasswordEncoder().encode(password));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
}
