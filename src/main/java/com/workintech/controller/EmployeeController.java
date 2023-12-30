package com.workintech.controller;

import com.workintech.entity.Employee;
import com.workintech.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }
    @GetMapping("/")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @GetMapping("/byOrder")
    public List<Employee> findByOrder(){
        return employeeService.findByOrder();
    }
    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id){
        return employeeService.findById(id);
    }
    @GetMapping("/byEmail/{email}")
    public Employee findByEmail(@PathVariable String email){
        return employeeService.findByEmail(email);
    }

    @GetMapping("/bySalary/{salary}")
    public List<Employee> findBySalary(@PathVariable Double salary){
        return employeeService.findBySalary(salary);
    }
    @DeleteMapping("/{id}")
    public Employee delete(@PathVariable Long id){
        return employeeService.delete(id);
    }
}
