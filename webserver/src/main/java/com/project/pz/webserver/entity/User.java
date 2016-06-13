package com.project.pz.webserver.entity;

import com.project.pz.webserver.model.UserModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Entity
@Table(name = "users", schema = "main")
public class User {

    @Id
    @SequenceGenerator(name="users_seq_gen", sequenceName="main.users_seq", allocationSize=1, schema = "main")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="users_seq_gen")
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    public User() {
    }

    public User(UserModel model) {
        this.id = model.getId();
        this.email = model.getEmail();
        this.passwordHash = new BCryptPasswordEncoder().encode(model.getPassword());
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}
