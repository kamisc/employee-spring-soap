package com.sewerynkamil.employeespringsoap.domain;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "employeeFiles")
public class EmployeeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pesel;
    private String street;
    private String city;
    private BigInteger zipCode;

    public EmployeeFile() {
    }

    public EmployeeFile(String pesel, String street, String city, BigInteger zipCode) {
        this.pesel = pesel;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigInteger getZipCode() {
        return zipCode;
    }

    public void setZipCode(BigInteger zipCode) {
        this.zipCode = zipCode;
    }
}
