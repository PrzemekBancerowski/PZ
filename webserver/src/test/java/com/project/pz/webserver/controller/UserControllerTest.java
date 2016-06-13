package com.project.pz.webserver.controller;

import com.project.pz.webserver.AbstractMvcTest;
import com.project.pz.webserver.model.UserModel;
import com.project.pz.webserver.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by Piotr Sołtysiak on 2016-06-13.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Transactional
public class UserControllerTest extends AbstractMvcTest {

    @InjectMocks
    private UserController userController;

    @Autowired
    private UserService userService;

    @Before
    public void init() {
        ReflectionTestUtils.setField(userController, "userService", userService);
    }

    @Test
    public void createUserTest() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setEmail("email@email.co");
        userModel.setPassword("password12345");

        MvcResult createUserResult = mockMvc.perform(post("/users/create")
                .content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userModel))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().encoding(EXPECTED_ENCODING))
                .andDo(print())
                .andReturn();

        UserModel createdUser = mapper.readValue(createUserResult.getResponse().getContentAsString(), UserModel.class);

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals(userModel.getEmail(), createdUser.getEmail());
        assertNull(createdUser.getPassword());

        UserModel foundUserById = new UserModel(userService.getUserById(createdUser.getId()));

        assertNotNull(foundUserById);
        assertEquals(createdUser.getId(), foundUserById.getId());
        assertEquals(createdUser.getEmail(), foundUserById.getEmail());
        assertEquals(createdUser.getPassword(), foundUserById.getPassword());

        UserModel foundUserByEmail = new UserModel(userService.getUserByEmail(createdUser.getEmail()));

        assertNotNull(foundUserByEmail);
        assertEquals(createdUser.getId(), foundUserByEmail.getId());
        assertEquals(createdUser.getEmail(), foundUserByEmail.getEmail());
        assertEquals(createdUser.getPassword(), foundUserByEmail.getPassword());
    }

    @Test
    public void loginAndLogoutTest() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setEmail("email@email.co");
        userModel.setPassword("password12345");

        mockMvc.perform(post("/users/create")
                .content(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userModel))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().encoding(EXPECTED_ENCODING))
                .andDo(print())
                .andReturn();

        mockMvc.perform(formLogin("/users/login").user("email", userModel.getEmail()).password(userModel.getPassword()))
                .andExpect(authenticated().withRoles("USER"));

        mockMvc.perform(logout("/users/logout")).andExpect(unauthenticated());

    }

    @Override
    protected Object[] getInjectedControllers() {
        return new Object[]{userController};
    }
}
