package com.rallyce.Petroleum_Inventario.repositories;

import com.rallyce.Petroleum_Inventario.TestsClasses;
import com.rallyce.Petroleum_Inventario.domain.entities.EmpleadoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class EmpleadoRepositoryTests {

    private EmpleadoRepository underTest;

    @Autowired
    public EmpleadoRepositoryTests(EmpleadoRepository underTest){
        this.underTest = underTest;
    }

    @Test
    @Transactional
    public void crearEmpleado(){

        EmpleadoEntity empleado_A = TestsClasses.empleado_A();
        underTest.save(empleado_A);

    }

}
