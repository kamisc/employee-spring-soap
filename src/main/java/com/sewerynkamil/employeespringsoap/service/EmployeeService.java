package com.sewerynkamil.employeespringsoap.service;

import com.sewerynkamil.employeespringsoap.domain.Employee;
import com.sewerynkamil.employeespringsoap.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public Employee getEmployeeBySurname(String surname) {
        return employeeRepository.findBySurname(surname);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmployeeById(Long id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
