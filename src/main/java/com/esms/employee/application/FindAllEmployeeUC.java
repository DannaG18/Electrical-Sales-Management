package com.esms.employee.application;

import com.esms.employee.domain.entity.Employee;
import com.esms.employee.domain.service.EmployeeService;
import java.util.List;

public class FindAllEmployeeUC {
    private EmployeeService employeeService;

    public FindAllEmployeeUC(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> execute() {
        return employeeService.findAllEmployee();
    }
}
