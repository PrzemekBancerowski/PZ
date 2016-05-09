package com.project.pz.webserver.controller;

import com.project.pz.webserver.entity.User;
import com.project.pz.webserver.model.user.UserModel;
import com.project.pz.webserver.model.user.UserRequest;
import com.project.pz.webserver.model.user.UserResponse;
import com.project.pz.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    UserResponse addUser(@RequestBody UserRequest userRequest) {
        User user = userService.create(userRequest.getEmail(), userRequest.getPassword());
        return null; // TODO
    }


}
