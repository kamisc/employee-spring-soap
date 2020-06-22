
package com.sewerynkamil.employeespringsoap.req_res.employee;

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
 *         &lt;element name="employeeType" type="{http://sewerynkamil.pl/employee}employeeType"/>
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
    "employeeType"
})
@XmlRootElement(name = "getEmployeeByIdResponse", namespace = "http://sewerynkamil.pl/employee")
public class GetEmployeeByIdResponse {

    @XmlElement(namespace = "http://sewerynkamil.pl/employee", required = true)
    protected EmployeeType employeeType;

    /**
     * Gets the value of the employeeType property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeType }
     *     
     */
    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    /**
     * Sets the value of the employeeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeType }
     *     
     */
    public void setEmployeeType(EmployeeType value) {
        this.employeeType = value;
    }

}
