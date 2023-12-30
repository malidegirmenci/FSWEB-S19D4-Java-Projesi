package com.workintech.service;

import com.workintech.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    Employee save(Employee employee);
    Employee delete(Long id);
    Employee findByEmail(String email);
    List<Employee> findBySalary(Double salary);
    List<Employee> findByOrder();
}
