package com.esms.employee_roles.application;

import com.esms.employee_roles.domain.entity.EmployeeRole;
import com.esms.employee_roles.domain.service.EmployeeRoleService;

public class CreateEmployeeRoleUC {
    private final EmployeeRoleService employeeRoleService;

    public CreateEmployeeRoleUC(EmployeeRoleService employeeRoleService) {
        this.employeeRoleService = employeeRoleService;
    }

    public void execute(EmployeeRole employeeRole) {
        employeeRoleService.createEmployeeRole(employeeRole);
    }
}
