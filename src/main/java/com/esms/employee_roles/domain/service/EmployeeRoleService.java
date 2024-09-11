package com.esms.employee_roles.domain.service;

import java.util.List;

import com.esms.employee_roles.domain.entity.EmployeeRole;

import java.util.Optional;

public interface EmployeeRoleService {
    void createEmployeeRole (EmployeeRole employeeRole);
    void deleteEmployeeRole (int id);
    Optional <EmployeeRole> findEmployeeRole (int id);
    void updateEmployeeRole (EmployeeRole employeeRole);
    List <EmployeeRole> findAllEmployeeRole();
}
