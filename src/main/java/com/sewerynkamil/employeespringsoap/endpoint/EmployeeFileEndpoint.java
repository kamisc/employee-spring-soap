package com.sewerynkamil.employeespringsoap.endpoint;

import com.sewerynkamil.employeespringsoap.domain.EmployeeFile;
import com.sewerynkamil.employeespringsoap.service.EmployeeFileService;
import com.sewerynkamil.employeespringsoap.soap.employeefile.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeFileEndpoint {

    public static final String NAMESPACE_URI_EMPLOYEE_FILE = "http://sewerynkamil.pl/employeeFile";

    private EmployeeFileService employeeFileService;

    @Autowired
    public EmployeeFileEndpoint(EmployeeFileService employeeFileService) {
        this.employeeFileService = employeeFileService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE_FILE, localPart = "getEmployeeFileByIdRequest")
    @ResponsePayload
    public GetEmployeeFileByIdResponse getEmployeeFileById(@RequestPayload GetEmployeeFileByIdRequest request) {
        GetEmployeeFileByIdResponse response = new GetEmployeeFileByIdResponse();
        EmployeeFile employeeFile = employeeFileService.getEmployeeFileById(request.getId());
        EmployeeFileType employeeFileType = new EmployeeFileType();
        BeanUtils.copyProperties(employeeFile, employeeFileType);
        response.setEmployeeFileType(employeeFileType);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI_EMPLOYEE_FILE, localPart = "addEmployeeFileRequest")
    @ResponsePayload
    public AddEmployeeFileResponse addEmployeeFile(@RequestPayload AddEmployeeFileRequest request) {
        AddEmployeeFileResponse response = new AddEmployeeFileResponse();
        EmployeeFileType newEmployeeFileType = new EmployeeFileType();
        ServiceStatus serviceStatus = new ServiceStatus();

        EmployeeFile newEmployeeFile = new EmployeeFile();
        EmployeeFile savedEmployeeFile = employeeFileService.addEmployeeFile(newEmployeeFile);

        if (savedEmployeeFile == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding new Employee File");
        } else {
            BeanUtils.copyProperties(savedEmployeeFile, newEmployeeFileType);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Employee File added successfully");
        }

        response.setEmployeeFileType(newEmployeeFileType);
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
