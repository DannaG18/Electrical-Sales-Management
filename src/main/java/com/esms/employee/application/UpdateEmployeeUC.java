package com.esms.employee.application;

import com.esms.employee.domain.entity.Employee;
import com.esms.employee.domain.service.EmployeeService;

public class UpdateEmployeeUC {
    private EmployeeService employeeService;

    public UpdateEmployeeUC(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void execute(Employee employee) {
        employeeService.updateEmployee(employee);
    }
}
