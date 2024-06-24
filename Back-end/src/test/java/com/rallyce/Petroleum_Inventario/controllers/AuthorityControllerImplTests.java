package com.rallyce.Petroleum_Inventario.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rallyce.Petroleum_Inventario.TestsClasses;
import com.rallyce.Petroleum_Inventario.domain.entities.AuthoritySecurityEntity;
import com.rallyce.Petroleum_Inventario.domain.entities.UserSecurityEntity;
import com.rallyce.Petroleum_Inventario.services.AuthoritySecurityService;
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
public class AuthorityControllerImplTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private AuthoritySecurityService underTest;

    @Autowired
    public AuthorityControllerImplTests(MockMvc mockMvc, AuthoritySecurityService underTest){

        this.mockMvc = mockMvc;
        this.underTest = underTest;
        this.objectMapper = new ObjectMapper();

    }

    @Test
    @Transactional
    public void testThtatCreateAuthorityeReturnsHttpStatus201() throws Exception{

        AuthoritySecurityEntity authority_A = TestsClasses.authority_A(null);

        String userJson = objectMapper.writeValueAsString(authority_A);

        mockMvc.perform(MockMvcRequestBuilders.put("/authority")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
        ).andExpect(

                MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    @Transactional
    public void testThatCreateAuthorityReturnsCreatedAuthority() throws Exception{

        AuthoritySecurityEntity authority_A = TestsClasses.authority_A(null);

        String userJson = objectMapper.writeValueAsString(authority_A);

        mockMvc.perform(MockMvcRequestBuilders.put("/authority")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
        ).andExpect(

                MockMvcResultMatchers.jsonPath("$.authority").value(authority_A.getAuthority())
        );

    }

    @Test
    @Transactional
    public void testThatAuthoritiesListReturnsList() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/authority")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(

                MockMvcResultMatchers.jsonPath("$[0].authority").value("ROLE_ADMINISTRADOR")
        );

    }

    @Test
    @Transactional
    public void testThatGetAuthorityByAuthorityReturnsHttpStatus200() throws Exception{


        AuthoritySecurityEntity authority_A = TestsClasses.authority_A(null);

        String authorityJson = objectMapper.writeValueAsString(authority_A);

        mockMvc.perform(MockMvcRequestBuilders.get("/authority/" + authority_A.getAuthority())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorityJson)

        ).andExpect(

                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    @Transactional
    public void testThatGetAuthorityByAuthorityReturnsHttpStatus404() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/authority/ROLE_ASISTENTE")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    @Transactional
    public void testThatGetAuthorityByAuthorityReturnsAuthority() throws Exception{

        UserSecurityEntity user_A = TestsClasses.user_A();

        AuthoritySecurityEntity authority_A = TestsClasses.authority_A(null);

        String authorityJson = objectMapper.writeValueAsString(authority_A);

        mockMvc.perform(MockMvcRequestBuilders.get("/authority/" + authority_A.getAuthority())
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorityJson)
        ).andExpect(

                MockMvcResultMatchers.jsonPath("$.authority").value("ROLE_ADMINISTRADOR")
        );
    }

    @Test
    @Transactional
    public void testThatFullUpdateAuthorityReturnsHttpStatus201() throws Exception{

        AuthoritySecurityEntity authority_A= TestsClasses.authority_A(null);

        String userJson = objectMapper.writeValueAsString(authority_A);

        mockMvc.perform(MockMvcRequestBuilders.put("/authority/ROLE_ADMINISTRADOR")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    @Transactional
    public void testThatDeleteaAuthorityReturnsHttpStatus204() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/ROLE_TECNICO")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );

    }


}
