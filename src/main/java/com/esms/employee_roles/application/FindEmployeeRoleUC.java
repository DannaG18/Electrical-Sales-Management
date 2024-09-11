package com.esms.employee_roles.application;

import java.util.Optional;

import com.esms.employee_roles.domain.entity.EmployeeRole;
import com.esms.employee_roles.domain.service.EmployeeRoleService;

public class FindEmployeeRoleUC {
    private final EmployeeRoleService employeeRoleService;

    public FindEmployeeRoleUC(EmployeeRoleService employeeRoleService) {
        this.employeeRoleService = employeeRoleService;
    }

    public Optional <EmployeeRole> execute(int id) {
        return employeeRoleService.findEmployeeRole(id);
    }
}
