package com.example.demo.service.concepto5;

import com.example.demo.domain.SmartPhone;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LifeCycleTest {

    @BeforeEach
    void setCpu(){
        System.out.println("Setup");
    }

    @Test
    @DisplayName("contar numero de smartphones")
    void countTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        Integer num = service.count();

        // si falla uno, el siguiento ya no ejecutaria
        assertNotNull(num);
        assertTrue(num > 0);
        assertEquals(3, num);
    }

    @Test
    @DisplayName("buscar todos los smartphones")
    void findAllTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        List<SmartPhone> smartphones = service.findAll();

        assertNotNull(smartphones);
        assertEquals(3,smartphones.size());

    }
}
