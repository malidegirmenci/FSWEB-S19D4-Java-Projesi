package com.workintech.service;

import com.workintech.entity.Employee;
import com.workintech.exception.EmployeeException;
import com.workintech.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        throw new EmployeeException("Employee with given id does not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee delete(Long id) {
        Employee willDeleteEmployee = findById(id);
        employeeRepository.delete(willDeleteEmployee);
        return willDeleteEmployee;
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        throw new EmployeeException("Employee with given email does not exist",HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Employee> findBySalary(Double salary) {
        return employeeRepository.findBySalary(salary);
    }

    @Override
    public List<Employee> findByOrder() {
        return employeeRepository.findByOrder();
    }
}
