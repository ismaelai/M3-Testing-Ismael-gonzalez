package com.example.demo.junit;

import com.example.demo.service.IRPFCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IRPFCalculatorTest {

    IRPFCalculator service = new IRPFCalculator();

    @Test
    void IRPFCalculatorTest() {

        double amount = service.calculateIRPF(15);
        assertEquals(2.25, amount);
    }

    @Test
    void IRPFCalculatorCeroTest() {
        double amount = service.calculateIRPF(0);
        assertEquals(0, amount);
    }

    @Test
    void IRPFCalculator() {
        double amount = service.calculateIRPF(-1);
        assertEquals(-0.15, amount);
    }
}