package com.sewerynkamil.employeespringsoap.endpoint;

import com.sewerynkamil.employeespringsoap.domain.Employee;
import com.sewerynkamil.employeespringsoap.service.EmployeeService;
import com.sewerynkamil.employeespringsoap.soap.employee.GetEmployeeByIdRequest;
import com.sewerynkamil.employeespringsoap.soap.employee.GetEmployeeByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class EmployeeEndpoint {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public GetEmployeeByIdResponse getEmployeeById(GetEmployeeByIdRequest request) {
        GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();
        Employee employee = employeeService.getEmployeeById(request.getId());


    }





}
