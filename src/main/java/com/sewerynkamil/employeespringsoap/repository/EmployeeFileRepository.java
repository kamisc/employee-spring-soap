package com.sewerynkamil.employeespringsoap.repository;

import com.sewerynkamil.employeespringsoap.domain.EmployeeFile;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface EmployeeFileRepository extends JpaRepository<EmployeeFile, Long> {
}
