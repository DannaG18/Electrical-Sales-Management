package com.esms.users_roles.application;

import java.util.Optional;

import com.esms.users_roles.domain.entity.UsersRoles;
import com.esms.users_roles.domain.service.UsersRolesService;

public class FindUsersRolesUseCase {
    private UsersRolesService usersRolesService;

    public FindUsersRolesUseCase(UsersRolesService usersRolesService) {
        this.usersRolesService = usersRolesService;
    }

    public Optional<UsersRoles> execute(int users_id, int roles_id) {
        return usersRolesService.findUsersRoles(users_id, roles_id);
    }
}
