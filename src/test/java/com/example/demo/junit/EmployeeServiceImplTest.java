package com.example.demo.junit;

import com.example.demo.domain.Employee;
import com.example.demo.domain.SmartPhone;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import com.example.demo.service.SmartPhoneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test sin mockito
 *
 * Clase EmployeeServiceImpl con una dependencia EmployeeRepositoryImpl
 */
@DisplayName("Operaciones sobre la entidad Employee")
class EmployeeServiceImplTest {

    // SUT - System Under Test - La clase a testear
    EmployeeService service;

    @BeforeEach
    void setUp() {
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        service = new EmployeeServiceImpl(repository);
    }

    @DisplayName("count(): Contar número de empleados")
    @Test
    void count() {
        Integer count = service.count();
        assertNotNull(count);
        assertEquals(3, count);
    }

    @DisplayName("findAll(): Buscar todos los empleados")
    @Test
    void findAll() {
        List<Employee> employees = service.findAll();
        assertNotNull(employees);
        assertEquals(3, employees.size());
    }

    @DisplayName("Buscar un empleado con id 1")
    @Test
    void findOne1Test() {

        Employee employee = service.findOne(1L);

        assertNotNull(employee);
        assertEquals(1L, employee.getId());
        assertNotNull(employee.getName());
    }

    @DisplayName("Buscar un empleado que no existe")
    @Test
    void findOneNotExistTest() {
        Employee employee  = service.findOne(999L);
        assertNull(employee);
    }

    @DisplayName("Comprobar excepción al buscar un empleado nulo")
    @Test
    void findOneExceptionTest() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.findOne(null)
        );
    }

    @Test
    void findOne1Optional() {
        Optional<Employee> employeeOpt = service.findOneOptional(1L);
        assertTrue(employeeOpt.isPresent());

        Employee employee = employeeOpt.get();
        Long id = employee.getId();

        assertEquals(1L, id);
    }

    @Test
    void findOneNullOptional() {
        Optional<Employee> employeeOpt = service.findOneOptional(null);

        assertTrue(employeeOpt.isEmpty());
    }

    @Test
    void findOneNotExistOptional() {
        Optional<Employee> employeeOpt = service.findOneOptional(999L);

        assertTrue(employeeOpt.isEmpty());
    }

    @Test
    void save() {

        Employee employee = new Employee();
        Employee result = service.save(employee);
        assertNotNull(result);
    }

    @Test
    void saveNullTest() {
        Employee employee  = new Employee(null, "empleado null", 22);

        assertEquals(3, service.count());
        Employee result = service.save(employee);
        assertEquals(4, service.count());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());
    }

    @Test
    void saveCeroIdTest() {
        Employee employee  = new Employee(0L, "empledao cero", 33);
        Employee result = service.save(employee);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());
    }

    @Test
    void delete() {
        boolean result = service.delete(1L);
        assertTrue(result);
    }

    @Test
    void deleteNullTest() {
        boolean result = service.delete(null);
        assertFalse(result);
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        assertTrue(true);
    }
}