package com.sewerynkamil.employeespringsoap.service;

import com.sewerynkamil.employeespringsoap.domain.Employee;
import com.sewerynkamil.employeespringsoap.domain.exception.EmployeeNotFoundException;
import com.sewerynkamil.employeespringsoap.domain.exception.ServiceFaultException;
import com.sewerynkamil.employeespringsoap.repository.EmployeeRepository;
import com.sewerynkamil.employeespringsoap.req_res.employee.ServiceStatus;
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

    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with id " + id + " dosen't exist in the database!");
        }

        return employee;

    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public boolean updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        return true;
    }

    public ServiceStatus deleteEmployeeById(Long id) {
        ServiceStatus serviceStatus = new ServiceStatus();

        try {
            employeeRepository.deleteById(id);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Employee deleted successfully");
            return serviceStatus;
        } catch (Exception e) {
            String errorMessage = "ERROR";
            serviceStatus.setStatusCode("NOT_FOUND");
            serviceStatus.setMessage("Employee with id " + id + " not found. Cannot delete Employee!");

            throw new ServiceFaultException(errorMessage, serviceStatus);
        }
    }
}
