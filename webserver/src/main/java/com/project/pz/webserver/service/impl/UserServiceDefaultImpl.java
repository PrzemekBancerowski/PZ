package com.project.pz.webserver.service.impl;

import com.project.pz.webserver.entity.User;
import com.project.pz.webserver.model.UserModel;
import com.project.pz.webserver.repository.UserRepository;
import com.project.pz.webserver.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Piotr Sołtysiak on 2016-05-06.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class UserServiceDefaultImpl implements UserService {

    private final Logger logger = Logger.getLogger(UserServiceDefaultImpl.class);

    private final String ANONYMOUS_USER = "anonymousUser";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

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
        return userRepository.findOneByEmail(email);
    }

    public UserModel currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (ANONYMOUS_USER.equals(auth.getPrincipal().toString())) {
            return null;
        } else {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            if (user != null) {
                return new UserModel(getUserByEmail(user.getUsername()));
            }
            return null;
        }
    }

    @Override
    public UserModel login(UserModel model) {
        logger.info(String.format("UserEntity: %s authentication attempt...", model.getEmail()));

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(model.getEmail(), model.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.info(String.format("UserEntity: %s authenticated successfully.", model.getEmail()));

        model.setPassword(null);

        return model;
    }
}
