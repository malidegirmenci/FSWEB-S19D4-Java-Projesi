package com.workintech.repository;

import com.workintech.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class EmployeeRepositoryTest {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @BeforeEach
    void setUp(){
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("EfKennedy");
        employee1.setEmail("johnefkennedy@gmail.com");
        employee1.setSalary(30000d);

        Employee employee2 = new Employee();
        employee2.setFirstName("Marlyn");
        employee2.setLastName("Monroe");
        employee2.setEmail("marlynmonroe@gmail.com");
        employee2.setSalary(50000d);

        Employee employee3 = new Employee();
        employee3.setFirstName("Wife");
        employee3.setLastName("EfKennedy");
        employee3.setEmail("wifeefkennedy@gmail.com");
        employee3.setSalary(75000d);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        employeeRepository.saveAll(employeeList);
    }

    @AfterEach
    void tearDown(){
        employeeRepository.deleteAll();
    }
    @BeforeAll
    static void beforeAll(){
        System.out.println("Employee Repository Test has been started");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Employee Repository Test has been finished");
    }

    @Test
    @DisplayName(value = "Find employee with given email")
    void findByEmail(){
        //Control with non exist email
        String nonExistEmail = "kamile@gmail.com";
        Optional<Employee> optionalEmployee1 = employeeRepository.findByEmail(nonExistEmail);
        assertEquals(Optional.empty(),optionalEmployee1);

        //Control with exist email
        String existEmail = "marlynmonroe@gmail.com";
        Optional<Employee> optionalEmployee2 = employeeRepository.findByEmail(existEmail);
        assertNotNull(optionalEmployee2.get());
        assertEquals("Marlyn",optionalEmployee2.get().getFirstName());
        assertEquals(50000d,optionalEmployee2.get().getSalary());
    }

    @Test
    void findBySalary(){
        List<Employee> employees = employeeRepository.findBySalary(35000d);
        assertEquals(2,employees.size());

    }

    @Test
    void findByOrder(){
        List<Employee> employees = employeeRepository.findByOrder();
        assertEquals(3,employees.size());
        assertEquals("Marlyn",employees.get(2).getFirstName());
        assertEquals("Wife",employees.get(1).getFirstName());
    }
}
