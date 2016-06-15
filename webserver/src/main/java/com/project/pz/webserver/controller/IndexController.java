package com.project.pz.webserver.controller;

import com.project.pz.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Piotr Sołtysiak on 2016-06-16.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return userService.currentUser() == null ? "forward:/login.html" : "forward:/index.html";
    }
}
