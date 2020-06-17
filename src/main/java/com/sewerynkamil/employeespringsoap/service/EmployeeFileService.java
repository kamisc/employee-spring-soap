package com.sewerynkamil.employeespringsoap.service;

import com.sewerynkamil.employeespringsoap.domain.EmployeeFile;
import com.sewerynkamil.employeespringsoap.repository.EmployeeFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeFileService {

    private EmployeeFileRepository employeeFileRepository;

    @Autowired
    public EmployeeFileService(EmployeeFileRepository employeeFileRepository) {
        this.employeeFileRepository = employeeFileRepository;
    }

    public EmployeeFile getEmployeeFileById(Long id) {
        return employeeFileRepository.findById(id).get();
    }

    public EmployeeFile addEmployeeFile(EmployeeFile employeeFile) {
        try {
            return employeeFileRepository.save(employeeFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateEmployeeFile(EmployeeFile employeeFile) {
        try {
            employeeFileRepository.save(employeeFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmployeeFileById(Long id) {
        try {
            employeeFileRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
