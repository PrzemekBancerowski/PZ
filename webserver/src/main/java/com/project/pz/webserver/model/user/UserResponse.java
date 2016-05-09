package com.project.pz.webserver.model.user;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class UserResponse {

    private final Integer userId;

    private final String token;

    public UserResponse(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}
