package com.sewerynkamil.employeespringsoap.endpoint;

import com.sewerynkamil.employeespringsoap.domain.Employee;
import com.sewerynkamil.employeespringsoap.service.EmployeeService;
import com.sewerynkamil.employeespringsoap.soap.employee.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class EmployeeEndpoint {

    public static final String NAMESPACE_URI_EMPLOYEE = "http://sewerynkamil.pl/employee";

    private EmployeeService employeeService;

    @Autowired
    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeByIdResponse getEmployeeById(@RequestPayload GetEmployeeByIdRequest request) {
        GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();
        Employee employee = employeeService.getEmployeeById(request.getId());
        EmployeeType employeeType = new EmployeeType();
        BeanUtils.copyProperties(employee, employeeType);
        response.setEmployeeType(employeeType);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE, localPart = "getAllEmployeesRequest")
    @ResponsePayload
    public GetAllEmployeesResponse getAllEmployees(@RequestPayload GetAllEmployeesRequest request) {
        GetAllEmployeesResponse response = new GetAllEmployeesResponse();
        List<EmployeeType> employeeTypeList = new ArrayList<>();
        List<Employee> employeeList = employeeService.getAllEmployees();
        for (Employee employee : employeeList) {
            EmployeeType employeeType = new EmployeeType();
            BeanUtils.copyProperties(employee, employeeType);
            employeeTypeList.add(employeeType);
        }
        response.getEmployeeType().addAll(employeeTypeList);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
        AddEmployeeResponse response = new AddEmployeeResponse();
        EmployeeType newEmployeeType = new EmployeeType();
        ServiceStatus serviceStatus = new ServiceStatus();

        Employee newEmployee = new Employee(request.getName(), request.getSurname());
        Employee savedEmployee = employeeService.addEmployee(newEmployee);

        if (savedEmployee == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding new Employee");
        } else {
            BeanUtils.copyProperties(savedEmployee, newEmployeeType);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Employee added successfully");
        }

        response.setEmployeeType(newEmployeeType);
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Employee employeeFromDB = employeeService.getEmployeeById(request.getId());

        if (employeeFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Employee = " + request.getSurname() + " not found");
        } else {
            employeeFromDB.setName(request.getName());
            employeeFromDB.setSurname(request.getSurname());

            boolean flag = employeeService.updateEmployee(employeeFromDB);

            if (!flag) {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Exception while updating Employee = " + request.getSurname());
            } else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Employee updated successfully");
            }
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        boolean flag = employeeService.deleteEmployeeById(request.getId());

        if (!flag) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deleting Employee id = " + request.getId());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Employee deleted successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }
}
