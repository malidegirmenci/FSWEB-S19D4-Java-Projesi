package com.workintech.service;

import com.workintech.entity.Employee;
import com.workintech.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    @BeforeAll
    static void beforeAll(){
        System.out.println("Employee Service Tests has been started");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("Employee Service Test has been finished");
    }
    @BeforeEach
    void setup(){
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }
    @Test
    @DisplayName("Finds all employees")
    void findAll(){
        employeeService.findAll();
        verify(employeeRepository).findAll();
    }
    @Test
    @DisplayName("Finds all employees with ordered by last name")
    void findByOrder(){
        employeeService.findByOrder();
        verify(employeeRepository).findByOrder();
    }

    @Test
    void isSaveSucceed(){
        Employee employee = new Employee();
        employee.setEmail("tester@test.com");
        employee.setFirstName("Tester");
        employee.setLastName("Testesron");
        given(employeeRepository.save(employee)).willReturn(employee);
        Employee savedEmployee = employeeService.save(employee);
        assertNotNull(savedEmployee);
    }

    @Test
    void isSaveFailed(){
        Employee employee = new Employee();
        employee.setEmail("tester@test.com");
        employee.setFirstName("Tester");
        employee.setLastName("Testesron");
        Employee savedEmployee = employeeService.save(employee);
        assertNull(savedEmployee);
    }
}
