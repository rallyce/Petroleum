package com.rallyce.Petroleum_Inventario.repositories;


import com.rallyce.Petroleum_Inventario.TestsClasses;
import com.rallyce.Petroleum_Inventario.domain.entities.UserSecurityEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryImplTests {

    private UserSecurityRepository underTest;

    @Autowired
    public UserRepositoryImplTests(UserSecurityRepository underTest){

        this.underTest = underTest;

    }


    @Test
    @Transactional
    public void testAddingNewAuthor(){

        UserSecurityEntity user_A = TestsClasses.user_A();

        underTest.save(user_A);

    }

}
