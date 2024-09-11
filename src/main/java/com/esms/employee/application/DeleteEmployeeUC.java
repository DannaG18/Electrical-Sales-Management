package com.esms.employee.application;

import com.esms.employee.domain.service.EmployeeService;

public class DeleteEmployeeUC {
    private EmployeeService employeeService;

    public DeleteEmployeeUC(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void execute(int id) {
        employeeService.deleteEmployee(id);
    }
}
