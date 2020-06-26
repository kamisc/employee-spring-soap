package com.sewerynkamil.employeespringsoap.endpoint;

import com.sewerynkamil.employeespringsoap.domain.Employee;
import com.sewerynkamil.employeespringsoap.domain.EmployeeFile;
import com.sewerynkamil.employeespringsoap.mapper.EmployeeFileMapper;
import com.sewerynkamil.employeespringsoap.service.EmployeeFileService;
import com.sewerynkamil.employeespringsoap.req_res.employeefile.*;
import com.sewerynkamil.employeespringsoap.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeFileEndpoint {

    public static final String NAMESPACE_URI_EMPLOYEE_FILE = "http://sewerynkamil.pl/employeeFile";

    private EmployeeFileService employeeFileService;
    private EmployeeService employeeService;
    private EmployeeFileMapper employeeFileMapper;

    @Autowired
    public EmployeeFileEndpoint(EmployeeFileService employeeFileService, EmployeeService employeeService, EmployeeFileMapper employeeFileMapper) {
        this.employeeFileService = employeeFileService;
        this.employeeService = employeeService;
        this.employeeFileMapper = employeeFileMapper;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE_FILE, localPart = "getEmployeeFileByIdRequest")
    @ResponsePayload
    public GetEmployeeFileByIdResponse getEmployeeFileById(@RequestPayload GetEmployeeFileByIdRequest request) {
        GetEmployeeFileByIdResponse response = new GetEmployeeFileByIdResponse();
        response.setEmployeeFileType(
                employeeFileMapper.mapToEmployeeFileType(
                        employeeFileService.getEmployeeFileById(request.getId())));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE_FILE, localPart = "addEmployeeFileRequest")
    @ResponsePayload
    public AddEmployeeFileResponse addEmployeeFile(@RequestPayload AddEmployeeFileRequest request) {
        AddEmployeeFileResponse response = new AddEmployeeFileResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        EmployeeFile savedEmployeeFile = employeeFileService.addEmployeeFile(
                new EmployeeFile(
                        request.getEmployeeId(),
                        request.getPesel(),
                        request.getStreet(),
                        request.getCity(),
                        request.getZipCode()));

        Employee employee = employeeService.getEmployeeById(request.getEmployeeId());
        employee.setEmployeeFile(savedEmployeeFile);
        employeeService.updateEmployee(employee);

        if (savedEmployeeFile == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding new Employee File");
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Employee File added successfully");
        }

        response.setEmployeeFileType(employeeFileMapper.mapToEmployeeFileType(savedEmployeeFile));
        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE_FILE, localPart = "updateEmployeeFileRequest")
    @ResponsePayload
    public UpdateEmployeeFileResponse updateEmployeeFile(@RequestPayload UpdateEmployeeFileRequest request) {
        UpdateEmployeeFileResponse response = new UpdateEmployeeFileResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        EmployeeFile employeeFileFromDB = employeeFileService.getEmployeeFileById(request.getId());

        if (employeeFileFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Employee File = " + request.getId() + " not found");
        } else {
            employeeFileFromDB.setPesel(request.getPesel());
            employeeFileFromDB.setStreet(request.getStreet());
            employeeFileFromDB.setCity(request.getCity());
            employeeFileFromDB.setZipCode(request.getZipCode());

            boolean flag = employeeFileService.updateEmployeeFile(employeeFileFromDB);

            if (!flag) {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Exception while updating Employee File = " + request.getId());
            } else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Employee File updated successfully");
            }
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE_FILE, localPart = "deleteEmployeeFileRequest")
    @ResponsePayload
    public DeleteEmployeeFileResponse deleteEmployeeFile(@RequestPayload DeleteEmployeeFileRequest request) {
        DeleteEmployeeFileResponse response = new DeleteEmployeeFileResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        boolean flag = employeeFileService.deleteEmployeeFileById(request.getId());

        if (!flag) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deleting Employee File id = " + request.getId());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Employee File deleted successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

}
