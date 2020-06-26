package com.sewerynkamil.employeespringsoap.endpoint;

import com.sewerynkamil.employeespringsoap.domain.Employee;
import com.sewerynkamil.employeespringsoap.mapper.EmployeeMapper;
import com.sewerynkamil.employeespringsoap.service.EmployeeService;
import com.sewerynkamil.employeespringsoap.req_res.employee.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeEndpoint {

    public static final String NAMESPACE_URI_EMPLOYEE = "http://sewerynkamil.pl/employee";

    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeEndpoint(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeByIdResponse getEmployeeById(@RequestPayload GetEmployeeByIdRequest request) {
        GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();
        response.setEmployeeType(
                employeeMapper.mapToEmployeeType(
                        employeeService.getEmployeeById(request.getId())));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE, localPart = "getAllEmployeesRequest")
    @ResponsePayload
    public GetAllEmployeesResponse getAllEmployees(@RequestPayload GetAllEmployeesRequest request) {
        GetAllEmployeesResponse response = new GetAllEmployeesResponse();
        response.getEmployeeType().addAll(
                employeeMapper.mapToEmployeeTypeList(
                        employeeService.getAllEmployees()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE, localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
        AddEmployeeResponse response = new AddEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Employee savedEmployee = employeeService.addEmployee(new Employee(request.getName(), request.getSurname()));

        if (savedEmployee == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding new Employee");
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Employee added successfully");
        }

        response.setEmployeeType(employeeMapper.mapToEmployeeType(savedEmployee));
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
