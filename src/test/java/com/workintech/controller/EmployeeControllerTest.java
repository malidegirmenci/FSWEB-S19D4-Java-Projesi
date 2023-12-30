package com.workintech.controller;

import com.workintech.entity.Employee;
import com.workintech.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    @Test
    void save() throws Exception{
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmail("testeron@gmail.com");
        employee.setFirstName("Test Name");
        employee.setLastName("Test Surname");
        employee.setSalary(20000d);

        when(employeeService.save(employee)).thenReturn(employee);

        mockMvc.perform(post("/employees/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(20000d))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }
    private static String asJsonString(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
