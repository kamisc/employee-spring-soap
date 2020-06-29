package com.sewerynkamil.employeespringsoap.domain.exception;

import com.sewerynkamil.employeespringsoap.req_res.employee.ServiceStatus;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import javax.xml.namespace.QName;

public class DetailSoapDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {

    private static final QName CODE = new QName("statusCode");
    private static final QName MESSAGE = new QName("message");

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
        logger.warn("Exception processed", ex);
        if (ex instanceof ServiceFaultException) {
            ServiceStatus serviceStatus = ((ServiceFaultException) ex).getServiceStatus();
            SoapFaultDetail detail = fault.addFaultDetail();
            detail.addFaultDetailElement(CODE).addText(serviceStatus.getStatusCode());
            detail.addFaultDetailElement(MESSAGE).addText(serviceStatus.getMessage());
        }
    }
}
