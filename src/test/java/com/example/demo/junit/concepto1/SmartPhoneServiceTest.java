package com.example.demo.junit.concepto1;

import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.Camera;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartPhoneServiceTest {

    SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

    @Test
    void countSmartPhoneTest() {
        Integer num = service.count();
        assertNotNull(num);
        assertEquals(3, num);
    }

    @Test
    void findAllTest() {

        List<SmartPhone> smartphones = service.findAll();
        assertNotNull(smartphones);
        assertEquals(3, smartphones.size());

    }

    @Test
    void findAllNullTest() {

        List<SmartPhone> smartwatches = service.findAll();
        assertNotNull(smartwatches);
        assertEquals(3, smartwatches.size());

    }

    @Test
    void findOneTest() {
        SmartPhone phone = service.findOne(1L);
        assertNotNull(phone);
        assertEquals(1L, phone.getId());
    }


    @Test
    void findOneExceptionTest() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.findOne(null)
        );
    }


    @Test
    void findByWifiTest() {
        List<SmartPhone> smartphones = service.findByWifi(true);

        assertEquals(2, smartphones.size());
        assertTrue(smartphones.get(0).getWifi());
        for (SmartPhone smartphone : smartphones) {
            assertTrue(smartphone.getWifi());
        }
    }

    @Test
    void saveNullTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.save(null)
        );
    }

    @DisplayName("Comprobar que se asigna un id cuando el id que pasamos es null")
    @Test
    void saveIdNullTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        SmartPhone phone = new SmartPhone(null, "Otro smartphone",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        assertEquals(3, service.count());
        SmartPhone result = service.save(phone);
        assertEquals(4, service.count());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());

    }
    @DisplayName("Comprobar que se asigna un id cuando el id que pasamos es 0")
    @Test
    void saveIdZeroTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        SmartPhone phone = new SmartPhone(0L, "Another one!",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        assertEquals(3, service.count());
        SmartPhone result = service.save(phone);
        assertEquals(4, service.count());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());
    }

    @DisplayName("Comprobar que se actualiza un smartphone existente")
    @Test
    void saveUpdateTest(){

        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        SmartPhone phone = new SmartPhone(1L, "One plus 9 editado",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        // comprobar num smartphones
        assertEquals(3, service.count());
        SmartPhone result = service.save(phone);
        assertEquals(3, service.count()); // no se crea un smartphone, se actualiza uno que ya existe

        // comprobar id
        assertEquals(1L, result.getId());

        // comprobar name
        SmartPhone phone1 = service.findOne(1L);
        assertEquals("One plus 9 editado", phone1.getName());

    }
    @DisplayName("Comprobar id negativo, no se debería añadir un smartphone")
    @Test
    void saveNegativeIdTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();

        SmartPhone phone = new SmartPhone(-4L, "Smartphone pirata",
                new RAM(1L, "DDR4", 8),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                false,
                new Camera(1L, "front camera", 12.5));

        assertEquals(3, service.count());

        assertThrows(
                IllegalArgumentException.class,
                () -> service.save(phone)
        );

        assertEquals(3, service.count());


    }

    @Test
    void  getMaxSmartPhoneIdTest() {

    }

    @Test
    void deleteNullTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        boolean result = service.delete(null);
        assertFalse(result);
    }

    @Test
    void deleteNotContainsTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        boolean result = service.delete(666L);
        assertFalse(result);
    }

    @Test
    void deleteOkTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        boolean result = service.delete(1L);
        assertTrue(result);
    }

    @Test
    void deleteAllTest(){
        SmartPhoneServiceImpl service = new SmartPhoneServiceImpl();
        assertTrue(service.count() > 0);
        service.deleteAll();
        assertEquals(0, service.count());

    }

}
