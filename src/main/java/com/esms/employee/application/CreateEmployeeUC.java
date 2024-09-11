package com.esms.employee.application;

import com.esms.employee.domain.entity.Employee;
import com.esms.employee.domain.service.EmployeeService;

public class CreateEmployeeUC {
    private EmployeeService employeeService;

    public CreateEmployeeUC(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void execute (Employee employee) {
        employeeService.createEmployee(employee);
    }

    
}
