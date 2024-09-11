package com.esms.employee.domain.service;

import com.esms.employee.domain.entity.Employee;
import java.util.Optional;
import java.util.List;

public interface EmployeeService {
    void createEmployee (Employee employee);
    void deleteEmployee (int id);
    Optional <Employee> findEmployee (int id);
    void updateEmployee (Employee employee);
    List <Employee> findAllEmployee();
}
