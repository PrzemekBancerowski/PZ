package com.project.pz.webserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.TEXT_PLAIN_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public String getHello() {
        return "Hello, it's your webserver.";
    }
}
