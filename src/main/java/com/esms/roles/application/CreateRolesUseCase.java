package com.esms.roles.application;

import com.esms.roles.domain.entity.Roles;
import com.esms.roles.domain.service.RolesService;

public class CreateRolesUseCase {
    private RolesService rolesService;

    public CreateRolesUseCase(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public void execute(Roles roles) {
        rolesService.createRoles(roles);
    }
}
