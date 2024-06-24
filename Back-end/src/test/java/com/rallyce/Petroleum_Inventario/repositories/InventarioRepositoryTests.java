package com.rallyce.Petroleum_Inventario.repositories;

import com.rallyce.Petroleum_Inventario.TestsClasses;
import com.rallyce.Petroleum_Inventario.domain.entities.InventarioEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class InventarioRepositoryTests {

    private InventarioRepository underTest;

    @Autowired
    public InventarioRepositoryTests(InventarioRepository underTest){
        this.underTest = underTest;
    }

    @Test
    @Transactional
    public void crearProducto(){

        InventarioEntity producto_A = TestsClasses.producto_A(null);

        underTest.save(producto_A);
    }

}
