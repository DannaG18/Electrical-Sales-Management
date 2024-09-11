package com.esms.employee_roles.application;

import com.esms.employee_roles.domain.service.EmployeeRoleService;

public class DeleteEmployeeRoleUC {
    private final EmployeeRoleService employeeRoleService;

    public DeleteEmployeeRoleUC(EmployeeRoleService employeeRoleService) {
        this.employeeRoleService = employeeRoleService;
    }

    public void execute(int id) {
        employeeRoleService.deleteEmployeeRole(id);
    }
}
