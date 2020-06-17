package com.sewerynkamil.employeespringsoap.domain;

import javax.persistence.*;

@Entity
@Table(name = "employeeFiles")
public class EmployeeFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long pesel;
    private String street;
    private String city;
    private int zipCode;

    public EmployeeFile() {
    }

    public EmployeeFile(Long pesel, String street, String city, int zipCode) {
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

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
