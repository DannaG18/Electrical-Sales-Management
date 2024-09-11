package com.esms.employee_roles.application;

import com.esms.employee_roles.domain.entity.EmployeeRole;
import com.esms.employee_roles.domain.service.EmployeeRoleService;

public class UpdateEmployeeRoleUC {
    private final EmployeeRoleService employeeRoleService;

    public UpdateEmployeeRoleUC(EmployeeRoleService employeeRoleService) {
        this.employeeRoleService = employeeRoleService;
    }

    public void execute(EmployeeRole employeeRole) {
        employeeRoleService.updateEmployeeRole(employeeRole);
    }
}
