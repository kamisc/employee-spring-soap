
package com.sewerynkamil.employeespringsoap.soap.employeefile;

import java.math.BigInteger;
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
 *         &lt;element name="pesel" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="street" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="zipCode" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
    "pesel",
    "street",
    "city",
    "zipCode"
})
@XmlRootElement(name = "addEmployeeFileRequest", namespace = "http://sewerynkamil.pl/employeeFile")
public class AddEmployeeFileRequest {

    @XmlElement(namespace = "http://sewerynkamil.pl/employeeFile")
    protected long pesel;
    @XmlElement(namespace = "http://sewerynkamil.pl/employeeFile", required = true)
    protected String street;
    @XmlElement(namespace = "http://sewerynkamil.pl/employeeFile", required = true)
    protected String city;
    @XmlElement(namespace = "http://sewerynkamil.pl/employeeFile", required = true)
    protected BigInteger zipCode;

    /**
     * Gets the value of the pesel property.
     * 
     */
    public long getPesel() {
        return pesel;
    }

    /**
     * Sets the value of the pesel property.
     * 
     */
    public void setPesel(long value) {
        this.pesel = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the zipCode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZipCode() {
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZipCode(BigInteger value) {
        this.zipCode = value;
    }

}
