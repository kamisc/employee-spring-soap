
package com.sewerynkamil.employeespringsoap.soap.employeefile;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the employeeFile package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: employeeFile
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateEmployeeFileRequest }
     * 
     */
    public UpdateEmployeeFileRequest createUpdateEmployeeFileRequest() {
        return new UpdateEmployeeFileRequest();
    }

    /**
     * Create an instance of {@link DeleteEmployeeFileRequest }
     * 
     */
    public DeleteEmployeeFileRequest createDeleteEmployeeFileRequest() {
        return new DeleteEmployeeFileRequest();
    }

    /**
     * Create an instance of {@link GetEmployeeFileByIdRequest }
     * 
     */
    public GetEmployeeFileByIdRequest createGetEmployeeFileByIdRequest() {
        return new GetEmployeeFileByIdRequest();
    }

    /**
     * Create an instance of {@link AddEmployeeFileRequest }
     * 
     */
    public AddEmployeeFileRequest createAddEmployeeFileRequest() {
        return new AddEmployeeFileRequest();
    }

    /**
     * Create an instance of {@link GetEmployeeFileByIdResponse }
     * 
     */
    public GetEmployeeFileByIdResponse createGetEmployeeFileByIdResponse() {
        return new GetEmployeeFileByIdResponse();
    }

    /**
     * Create an instance of {@link EmployeeFileType }
     * 
     */
    public EmployeeFileType createEmployeeFileType() {
        return new EmployeeFileType();
    }

    /**
     * Create an instance of {@link AddEmployeeFileResponse }
     * 
     */
    public AddEmployeeFileResponse createAddEmployeeResponse() {
        return new AddEmployeeFileResponse();
    }

    /**
     * Create an instance of {@link ServiceStatus }
     * 
     */
    public ServiceStatus createServiceStatus() {
        return new ServiceStatus();
    }

    /**
     * Create an instance of {@link UpdateEmployeeFileResponse }
     * 
     */
    public UpdateEmployeeFileResponse createUpdateEmployeeFileResponse() {
        return new UpdateEmployeeFileResponse();
    }

    /**
     * Create an instance of {@link DeleteEmployeeFileResponse }
     * 
     */
    public DeleteEmployeeFileResponse createDeleteEmployeeFileResponse() {
        return new DeleteEmployeeFileResponse();
    }

}
