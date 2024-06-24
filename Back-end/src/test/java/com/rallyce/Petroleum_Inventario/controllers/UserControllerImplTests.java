package com.rallyce.Petroleum_Inventario.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rallyce.Petroleum_Inventario.TestsClasses;
import com.rallyce.Petroleum_Inventario.domain.dto.UserSecurityDto;
import com.rallyce.Petroleum_Inventario.domain.entities.UserSecurityEntity;
import com.rallyce.Petroleum_Inventario.services.UserSecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class UserControllerImplTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private UserSecurityService userSecurityService;



    @Autowired
    public UserControllerImplTests(MockMvc mockMvc, UserSecurityService userSecurityService){

        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.userSecurityService = userSecurityService;
    }

    @Test
    @Transactional
    public void testThatCreateUserReturnsHttpStatus201() throws Exception{

        UserSecurityEntity userA = TestsClasses.user_A();

        String userJson = objectMapper.writeValueAsString(userA);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)

        ).andExpect(

                MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    @Transactional
    public void testTthatCreateUserReturnsCreatedUser() throws Exception {

        UserSecurityEntity userA = TestsClasses.user_A();

        String userJson = objectMapper.writeValueAsString(userA);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)

        ).andExpect(

                MockMvcResultMatchers.jsonPath("$.username").value(userA.getUsername())

        ).andExpect(

                MockMvcResultMatchers.jsonPath("$.password").value(userA.getPassword())
        ).andExpect(

                MockMvcResultMatchers.jsonPath("$.enableUser").isNumber()
        );


    }

    @Test
    @Transactional
    public void testThatFindAllUsersReturnsUser() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(

                MockMvcResultMatchers.jsonPath("$[0].username").value("Omar Zuñiga Calderon")
        ).andExpect(

                MockMvcResultMatchers.jsonPath("$[0].password").value("345")
        ).andExpect(

                MockMvcResultMatchers.jsonPath("$[0].enableUser").value(1)

        );

    }

    @Test
    @Transactional
    public void testThatGetUserByUsernameReturnsHttpStatus200() throws Exception{

        UserSecurityEntity userA = TestsClasses.user_A();

        String userJson = objectMapper.writeValueAsString(userA);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userA.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)

        ).andExpect(

                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    @Transactional
    public void testThatGetUserByUsernameReturnsHttpStatus404() throws Exception{


        mockMvc.perform(MockMvcRequestBuilders.get("/users/Miguel" )
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(

                MockMvcResultMatchers.status().isNotFound()
        );

    }

    @Test
    @Transactional
    public void testThatGetUserByUsernameReturnsUser() throws Exception{

        UserSecurityEntity userA = TestsClasses.user_A();

        String userJson = objectMapper.writeValueAsString(userA);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userA.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)

        ).andExpect(

                MockMvcResultMatchers.jsonPath("$.username").value("Omar Zuñiga Calderon")
        ).andExpect(

                MockMvcResultMatchers.jsonPath("$.password").value("345")
        ).andExpect(

                MockMvcResultMatchers.jsonPath("$.enableUser").value(1)

        );

    }

    @Test
    @Transactional
    public void testThatUpdateUserReturnsHttpStatus200() throws Exception{

        UserSecurityEntity user_A = TestsClasses.user_A();

        String userJson = objectMapper.writeValueAsString(user_A);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + user_A.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    @Transactional
    public void testThatUpdateUserReturnsHttpStatus404() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/users/Miguel")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );

    }


    @Test
    @Transactional
    public void testThatFulUpdateUserUpdatesUser() throws Exception{

        UserSecurityEntity user_A = TestsClasses.user_A();

        UserSecurityEntity savedUserEntity = userSecurityService.crearUsuario(user_A);

        UserSecurityDto userDto = TestsClasses.user_A_Dto();

        String userDtoJson = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/Omar Zuñiga Calderon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.username").value("Omar Zuñiga Calderon")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.password").value(userDto.getPassword())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.enableUser").value(userDto.getRole())
        );

    }

    @Test
    @Transactional
    public void testThatDeleteUserReturnsHttpStatus204() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/Omar Zuñiga Calderon")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );

    }

}
