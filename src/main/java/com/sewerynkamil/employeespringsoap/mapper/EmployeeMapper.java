package com.sewerynkamil.employeespringsoap.mapper;

import com.sewerynkamil.employeespringsoap.domain.Employee;
import com.sewerynkamil.employeespringsoap.req_res.employee.EmployeeType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {
    public EmployeeType mapToEmployeeType(Employee employee) {
        EmployeeType employeeType = new EmployeeType();
        BeanUtils.copyProperties(employee, employeeType);
        return employeeType;
    }

    public List<EmployeeType> mapToEmployeeTypeList(List<Employee> employeeList) {
        List<EmployeeType> employeeTypeList = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeeType employeeType = new EmployeeType();
            BeanUtils.copyProperties(employee, employeeType);
            employeeTypeList.add(employeeType);
        }
        return employeeTypeList;
    }
}
