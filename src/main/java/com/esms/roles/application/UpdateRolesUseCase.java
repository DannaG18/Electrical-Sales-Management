package com.esms.roles.application;

import com.esms.roles.domain.entity.Roles;
import com.esms.roles.domain.service.RolesService;

public class UpdateRolesUseCase {
    private RolesService rolesService;

    public UpdateRolesUseCase(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public void execute(Roles roles) {
        rolesService.updateRoles(roles);
    }

}
