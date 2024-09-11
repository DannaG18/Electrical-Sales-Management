package com.esms.employee_roles.application;

import java.util.List;

import com.esms.employee_roles.domain.entity.EmployeeRole;
import com.esms.employee_roles.domain.service.EmployeeRoleService;

public class FindAllEmployeeRoleUC {
    private final EmployeeRoleService employeeRoleService;

    public FindAllEmployeeRoleUC(EmployeeRoleService employeeRoleService) {
        this.employeeRoleService = employeeRoleService;
    }

    public List <EmployeeRole> execute () {
        return employeeRoleService.findAllEmployeeRole();
    }
}
