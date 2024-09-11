package com.esms.users.application;

import com.esms.users.domain.entity.Users;
import com.esms.users.domain.service.UsersService;

public class UpdateUsersUseCase {
    private UsersService usersService;

    public UpdateUsersUseCase(UsersService usersService) {
        this.usersService = usersService;
    }

    public void execute(Users users) {
        usersService.updateUsers(users);
    }
}
