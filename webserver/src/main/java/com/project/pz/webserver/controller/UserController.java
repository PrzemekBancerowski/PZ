package com.project.pz.webserver.controller;

import com.project.pz.webserver.model.UserModel;
import com.project.pz.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel addUser(@RequestBody UserModel userModel) {
        return userService.create(userModel);
    }


}
