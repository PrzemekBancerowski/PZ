package com.project.pz.webserver.service.impl;

import com.google.common.collect.Lists;
import com.project.pz.webserver.entity.User;
import com.project.pz.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class UserDetailsServiceDefaultImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Username " + email + " not found");
        }

        List<GrantedAuthority> authorities = Lists.newArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordHash(), true, true, true, true, authorities);
    }
}
