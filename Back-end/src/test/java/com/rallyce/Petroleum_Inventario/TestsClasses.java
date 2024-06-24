package com.rallyce.Petroleum_Inventario;

import com.rallyce.Petroleum_Inventario.domain.dto.UserSecurityDto;
import com.rallyce.Petroleum_Inventario.domain.entities.AuthoritySecurityEntity;
import com.rallyce.Petroleum_Inventario.domain.entities.EmpleadoEntity;
import com.rallyce.Petroleum_Inventario.domain.entities.InventarioEntity;
import com.rallyce.Petroleum_Inventario.domain.entities.UserSecurityEntity;

public class TestsClasses {

    public static UserSecurityEntity user_A(){

        return UserSecurityEntity.builder()
                .username("Omar Zuñiga Calderon")
                .password("345")
                .role("ADMINISTRADOR")
                .build();

    }

    public static UserSecurityDto user_A_Dto(){

        return UserSecurityDto.builder()
                .username("Omar Zuñiga Calderon")
                .password("3456")
                .role("ADMINISTRADOR")
                .build();

    }

    public static AuthoritySecurityEntity authority_A(UserSecurityEntity userSecurityEntity){

        return AuthoritySecurityEntity.builder()
                .username(userSecurityEntity)
                .authority("ROLE_ADMINISTRADOR")
                .build();
    }

    public static EmpleadoEntity empleado_A(){

        return EmpleadoEntity.builder()
                .nombre("Omar Yesid")
                .pais("Zuñiga Calderon")
                .build();
    }

    public static InventarioEntity producto_A(EmpleadoEntity empleadoEntity){

        return InventarioEntity.builder()
                .id("PR-1")
                .producto("Blower de pellets")
                .fecha("23/04/1990")
                .descripcion("Blower de la Formadora SP, se hace un cambio de conexion de delta a estrella para probar" +
                        "el funcionamiento del motor. Todo ok.")
                .build();
    }


}
