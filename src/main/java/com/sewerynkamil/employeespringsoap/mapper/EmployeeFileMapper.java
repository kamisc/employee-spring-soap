package com.sewerynkamil.employeespringsoap.mapper;

import com.sewerynkamil.employeespringsoap.domain.EmployeeFile;
import com.sewerynkamil.employeespringsoap.req_res.employeefile.EmployeeFileType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeFileMapper {
    public EmployeeFileType mapToEmployeeFileType(EmployeeFile employeeFile) {
        EmployeeFileType employeeFileType = new EmployeeFileType();
        BeanUtils.copyProperties(employeeFile, employeeFileType);
        return employeeFileType;
    }
}
