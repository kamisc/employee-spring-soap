package com.sewerynkamil.employeespringsoap.domain.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + EmployeeNotFoundException.NAMESPACE_URI + "} WRONG_ID_NUMBER")
public class EmployeeNotFoundException extends Exception {

    public static final String NAMESPACE_URI = "http://sewerynkamil.pl/employee";

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
