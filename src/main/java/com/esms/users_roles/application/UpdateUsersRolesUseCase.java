package com.esms.users_roles.application;

import com.esms.users_roles.domain.entity.UsersRoles;
import com.esms.users_roles.domain.service.UsersRolesService;

public class UpdateUsersRolesUseCase {
    private UsersRolesService usersRolesService;

    public UpdateUsersRolesUseCase(UsersRolesService usersRolesService) {
        this.usersRolesService = usersRolesService;
    }

    public void execute(UsersRoles usersRoles) {
        usersRolesService.updateUsersRoles(usersRoles);
    }
}
