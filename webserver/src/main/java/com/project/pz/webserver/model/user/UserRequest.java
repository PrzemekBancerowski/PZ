package com.project.pz.webserver.model.user;

import com.google.common.base.CharMatcher;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class UserRequest {

    private final String email;

    private final String password;

    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "email='" + email + '\'' +
                ", password='" + CharMatcher.ANY.replaceFrom(password, '*') + '\'' +
                '}';
    }
}
