
package com.sewerynkamil.employeespringsoap.req_res.employeefile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeFileType" type="{http://sewerynkamil.pl/employeeFile}employeeFileType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "employeeFileType"
})
@XmlRootElement(name = "getEmployeeFileByIdResponse", namespace = "http://sewerynkamil.pl/employeeFile")
public class GetEmployeeFileByIdResponse {

    @XmlElement(namespace = "http://sewerynkamil.pl/employeeFile", required = true)
    protected EmployeeFileType employeeFileType;

    /**
     * Gets the value of the employeeFileType property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeFileType }
     *     
     */
    public EmployeeFileType getEmployeeFileType() {
        return employeeFileType;
    }

    /**
     * Sets the value of the employeeFileType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeFileType }
     *     
     */
    public void setEmployeeFileType(EmployeeFileType value) {
        this.employeeFileType = value;
    }

}
