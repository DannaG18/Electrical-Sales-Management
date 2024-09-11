package com.esms.employee.application;

import com.esms.employee.domain.entity.Employee;
import com.esms.employee.domain.service.EmployeeService;
import java.util.Optional;

public class FindEmployeeUC {
    private EmployeeService employeeService;

    public FindEmployeeUC(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Optional<Employee> execute(int id) {
        return employeeService.findEmployee(id);
    }
}
