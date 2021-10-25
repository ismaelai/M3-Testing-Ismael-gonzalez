package com.example.demo.junit;

import com.example.demo.domain.Employee;
import com.example.demo.service.IRPFCalculator;
import com.example.demo.service.IVACalculator;
import com.example.demo.service.SalaryCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryCalculatorServiceTest {

    IRPFCalculator irpfCalculator;
    IVACalculator ivaCalculator;
    SalaryCalculatorService salaryCalculatorService;
    Employee employee;

    @BeforeEach
    void setUp() {
        irpfCalculator = new IRPFCalculator();
        ivaCalculator = new IVACalculator();
        salaryCalculatorService = new SalaryCalculatorService(irpfCalculator,ivaCalculator);
        employee = new Employee();
    }

    @Test
    void SalaryCalculatorNotNull(){
        SalaryCalculatorService salaryCalculatorService = new SalaryCalculatorService(irpfCalculator,ivaCalculator);
        assertNotNull(salaryCalculatorService);

    }

    @Test
    void calculateSalary() {
        employee.setAge(20);
        assertNotNull(employee.getAge());
        double salary = salaryCalculatorService.calculateSalary(employee);
        assertEquals(44528, salary);
    }
}