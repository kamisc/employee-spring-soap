package com.sewerynkamil.employeespringsoap.repository;

import com.sewerynkamil.employeespringsoap.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findBySurname(String surname);
}
