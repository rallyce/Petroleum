package com.rallyce.Petroleum_Inventario.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rallyce.Petroleum_Inventario.TestsClasses;
import com.rallyce.Petroleum_Inventario.domain.entities.EmpleadoEntity;
import com.rallyce.Petroleum_Inventario.repositories.EmpleadoRepository;
import com.rallyce.Petroleum_Inventario.services.EmpleadoService;
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
public class EmpleadoControllerImplTests {

    private EmpleadoService underTest;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    @Autowired
    public EmpleadoControllerImplTests(EmpleadoService underTest, MockMvc mockMvc){
        this.underTest = underTest;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @Transactional
    public void testThatCrearEmpleadoReturnsHttpStatus201() throws Exception{

        EmpleadoEntity empleado_A = TestsClasses.empleado_A();

        String empleadoJson = objectMapper.writeValueAsString(empleado_A);

        mockMvc.perform(MockMvcRequestBuilders.post("/empleado")
                .contentType(MediaType.APPLICATION_JSON)
                .content(empleadoJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated()

        );

    }

    @Test
    @Transactional
    public void testThatCrearEmpleadoPutsCreatedEmpleado() throws Exception{

        EmpleadoEntity empleado_A = TestsClasses.empleado_A();

        String empleadoJson = objectMapper.writeValueAsString(empleado_A);

        mockMvc.perform(MockMvcRequestBuilders.post("/empleado")
                .contentType(MediaType.APPLICATION_JSON)
                .content(empleadoJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(empleado_A.getNombre())

        ).andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value(empleado_A.getPais())

        );

    }

    @Test
    @Transactional
    public void testThatListEmpleadoReturnsHttpStatus200() throws Exception{


        mockMvc.perform(MockMvcRequestBuilders.get("/empleado")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()

        );

    }

    @Test
    @Transactional
    public void testThatListaEmpleadoReturnList() throws Exception{

        EmpleadoEntity empleado_A = TestsClasses.empleado_A();


        mockMvc.perform(MockMvcRequestBuilders.get("/empleado")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value(empleado_A.getNombre())

        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].apellido").value(empleado_A.getPais())

        );

    }

    @Test
    @Transactional
    public void testThatEmpleadoEspReturnsHttpStatus200() throws Exception{


        mockMvc.perform(MockMvcRequestBuilders.get("/empleado/2")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()

        );

    }

    @Test
    @Transactional
    public void testThatEmpleadoEspReturnEmpleado() throws Exception{

        EmpleadoEntity empleado_A = TestsClasses.empleado_A();


        mockMvc.perform(MockMvcRequestBuilders.get("/empleado/2")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(empleado_A.getNombre())

        ).andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value(empleado_A.getPais())

        );

    }

    @Test
    @Transactional
    public void testThatEActualizarEmpleadoReturnsHttpStatus200() throws Exception{

        EmpleadoEntity empleado_A = TestsClasses.empleado_A();

        String empleadoJson = objectMapper.writeValueAsString(empleado_A);

        mockMvc.perform(MockMvcRequestBuilders.put("/empleado/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(empleadoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk()

        );

    }

    @Test
    @Transactional
    public void testThatActualizarEmpleadoPutsEmpleado() throws Exception{

        EmpleadoEntity empleado_A = TestsClasses.empleado_A();

        String empleadoJson = objectMapper.writeValueAsString(empleado_A);

        mockMvc.perform(MockMvcRequestBuilders.put("/empleado/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(empleadoJson)

        ).andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(empleado_A.getNombre())

        ).andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value(empleado_A.getPais())

        );

    }

    @Test
    @Transactional
    public void testThatEliminarEmpleadoReturnsHttpStatus204() throws Exception{


        mockMvc.perform(MockMvcRequestBuilders.delete("/empleado/2")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent()

        );

    }





}
