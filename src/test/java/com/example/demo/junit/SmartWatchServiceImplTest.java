package com.example.demo.junit;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartWatch;
import com.example.demo.domain.pieces.Battery;
import com.example.demo.domain.pieces.CPU;
import com.example.demo.domain.pieces.HealthMonitor;
import com.example.demo.domain.pieces.RAM;
import com.example.demo.service.SmartWatchService;
import com.example.demo.service.SmartWatchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartWatchServiceImplTest {


    //@BeforeEach
    //void setUp() {
    SmartWatchServiceImpl service = new SmartWatchServiceImpl();
    //}

    @Test
    void count() {

        Integer count = service.count();
        assertNotNull(count);
        assertEquals(3, count);

    }

    @Test
    void findAll() {

        List<SmartWatch> smartWatches = service.findAll();
        assertNotNull(smartWatches);
        assertEquals(3, smartWatches.size());

    }

    @DisplayName("Find by ID")
    @Test
    void findOne() {

        SmartWatch smartWatch = service.findOne(1L);
        assertNotNull(smartWatch);
        assertEquals(1L, smartWatch.getId());
        assertNotNull(smartWatch.getName());

    }

    @Test
    void saveIdNullTest() {

        SmartWatch smartWatch1 =
                new SmartWatch(null, "Huawei",
                        new RAM(1L,"xxx", 2),
                        new Battery(1L, 555.0),
                        new CPU(1L, 3),
                        true,
                        new HealthMonitor(1L,0.6,2));

        assertEquals(3,service.count());
        SmartWatch result = service.save(smartWatch1);
        assertNotNull(result);
        assertEquals(4,service.count());
    }

    @Test
    void saveCeroIdTest() {
        SmartWatch smartwatch1 = new SmartWatch(0L, "Fitbit sense",
                new RAM(1L, "DDR4", 2),
                new Battery(1L, 4500.0),
                new CPU(1L, 4),
                true,
                new HealthMonitor(1L, 0.0, 0));

        SmartWatch result = service.save(smartwatch1);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4,result.getId());

    }
    @Test
    void  deleteNullTest() {

        boolean result = service.delete(null);
        assertFalse(result);
    }

    @Test
    void deleteNotContainsTest() {

        boolean result = service.delete(-1L);
        assertFalse(result);
    }

    @Test
    void deleteOkTest() {

        boolean result = service.delete(1L);
        assertTrue(result);
    }

    @Test
    void deleteAllTest() {

        assertTrue(service.count()>0);
        service.deleteAll();
        assertEquals(0,service.count());
    }
}