package com.esms.users_roles.application;

import com.esms.users_roles.domain.service.UsersRolesService;

public class DeleteUsersRolesUseCase {
    private UsersRolesService usersRolesService;

    public DeleteUsersRolesUseCase(UsersRolesService usersRolesService) {
        this.usersRolesService = usersRolesService;
    }

    public void execute(int users_id, int roles_id) {
        usersRolesService.deleteUsersRoles(users_id, roles_id);
    }

}
