package com.example.demo.mockito;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImpl2Test {

    // dependencia @Mock
    EmployeeRepositoryImpl repositoryMock;

    // Clase bajo testing - dependiente (depende de EmployeeRepository) @InjectMocks
    EmployeeServiceImpl service; // SUT - System Under Test

    @BeforeEach
    void setUp() {
        repositoryMock = mock(EmployeeRepositoryImpl.class); // objeto ficticio creado por Mockito
        service = new EmployeeServiceImpl(repositoryMock);
    }

    @Test
    void count() {

        // configuracion escenario
        when(repositoryMock.count()).thenReturn(5);

        // ejecutar el comportamiento a testear
        Integer result = service.count();

        // Aserciones y verificaciones
        assertNotNull(result);
        assertEquals(5, result);
    }

    @Test
    void findAll() {
        // configuracion escenario mock

        List<Employee> employees = Arrays.asList(
                new Employee(1L,"X",40),
                new Employee(  2L,"X",40),
                new Employee(3L,"X",40)
        );

        when(repositoryMock.findAll()).thenReturn(employees);

        // ejecutar el comportamiento a testear

        List<Employee> result = service.findAll();

        // Aserciones y verificaciones

        assertNotNull(result);
        assertEquals(3, result.size());

        verify(repositoryMock).findAll();

    }

    @Test
    void findOne() {

        Employee employee = new Employee();
        when(repositoryMock.findOne(1L)).thenReturn(employee);

        service.findOne(1L);

        assertNotNull(employee);

        verify(repositoryMock).findOne(anyLong());

    }

    @Test
    void findOneOptional() {

        // configuracion
        Employee employee = new Employee(1L, "e1", 24);

        when(repositoryMock.findOne(anyLong())).thenReturn(employee);

        // comportamiento
        Optional<Employee> employeeOpt = service.findOneOptional(1L);

        // verificar

        assertTrue(employeeOpt.isPresent());
        verify(repositoryMock).findOne(anyLong());

    }


    @Test
    void save() {

        Employee employee = new Employee(1L, "e1", 43);
        when(repositoryMock.save(any())).thenReturn(employee);

        Employee result = service.save(employee);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repositoryMock).save(any());
    }

    @Test
    void saveEmployeeNullTest(){
        Employee employee = new Employee(null, "NuevoEmpleado", 30);

        when(repositoryMock.save(any())).thenReturn(null);

        Employee result = service.save(employee);

        assertNull(result);


    }

    @Test
    void delete() {

        when(repositoryMock.delete(any())).thenReturn(true);

        boolean result = service.delete(1L);

        assertTrue(result);
        verify(repositoryMock).delete(any());
    }

    @Test
    void deleteAll() {

        service.deleteAll();
        verify(repositoryMock).deleteAll();
    }
}