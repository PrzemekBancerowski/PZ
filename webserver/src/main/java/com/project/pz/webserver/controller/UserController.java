package com.project.pz.webserver.controller;

import com.project.pz.webserver.entity.User;
import com.project.pz.webserver.model.request.UserRequest;
import com.project.pz.webserver.model.response.UserResponse;
import com.project.pz.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

     //   UserResponse userResponse = new UserResponse();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
           // String currentUserName = authentication.get();
           // return currentUserName;
        }

        return null; // TODO
    }


}
