package com.example.demo.junit;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.service.SmartDeviceFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartDeviceFactoryTest {

    @Test
    void createByType() {
        SmartDevice result = SmartDeviceFactory.createByType("phone");

        assertNotNull(result);
        assertNotNull(result.getCpu());
        assertTrue(result.getCpu().getOn());
        assertNotNull(result.getRam());
        assertNotNull(result.getBattery());

        assertTrue(result instanceof SmartPhone);
        SmartPhone smartphone = (SmartPhone) result;
        assertNotNull(smartphone.getCamera());

    }

    @Test
    void createByTypeSmartWatch() {

        SmartDevice result = SmartDeviceFactory.createByType("watch");

        assertNotNull(result);
        assertNotNull(result.getCpu());
        assertFalse(result.getCpu().getOn());
        assertNotNull(result.getRam());
        assertNotNull(result.getBattery());

        assertTrue(result instanceof SmartWatch);
        SmartWatch watch = (SmartWatch) result;
        assertNotNull(watch.getMonitor());

    }

    @Test
    void creatByTypeException(){

        assertThrows(IllegalArgumentException.class,
                ()->SmartDeviceFactory.createByType("tablet"));
    }
}