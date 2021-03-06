package com.sewerynkamil.employeespringsoap.domain;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EmployeeFile employeeFile;

    public Employee() {
    }

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public EmployeeFile getEmployeeFile() {
        return employeeFile;
    }

    public void setEmployeeFile(EmployeeFile employeeFile) {
        this.employeeFile = employeeFile;
    }
}
