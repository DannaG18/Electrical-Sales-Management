package com.esms.roles.application;

import java.util.Optional;

import com.esms.roles.domain.entity.Roles;
import com.esms.roles.domain.service.RolesService;

public class FindRolesUseCase {
    private RolesService rolesService;

    public FindRolesUseCase(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public Optional<Roles> execute(int id) {
        return rolesService.findRoles(id);
    }
}
