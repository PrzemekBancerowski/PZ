package com.project.pz.webserver.model;

import com.project.pz.webserver.entity.User;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class UserModel {

    private Integer id;

    private String email;

    private String password;

    public UserModel() {
    }

    public UserModel(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
