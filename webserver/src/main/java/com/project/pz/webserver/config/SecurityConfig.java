package com.project.pz.webserver.config;

import com.project.pz.webserver.security.RESTAuthenticationEntryPoint;
import com.project.pz.webserver.security.RESTAuthenticationFailureHandler;
import com.project.pz.webserver.security.RESTAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Created by Piotr Sołtysiak on 2016-05-09.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private RESTAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .and().csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().formLogin().loginProcessingUrl("/users/login").usernameParameter("email").permitAll()
                .and().logout().logoutUrl("/users/logout").logoutSuccessUrl("/").permitAll()
                .and().authorizeRequests().antMatchers("/users/**").permitAll()//.antMatchers("/**").authenticated()
                .and().formLogin().successHandler(authenticationSuccessHandler)
                .and().formLogin().failureHandler(authenticationFailureHandler);
    }
}
