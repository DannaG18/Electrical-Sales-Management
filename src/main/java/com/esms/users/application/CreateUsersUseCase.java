package com.esms.users.application;

import com.esms.users.domain.entity.Users;
import com.esms.users.domain.service.UsersService;

public class CreateUsersUseCase {
    private UsersService usersService;

    public CreateUsersUseCase(UsersService usersService) {
        this.usersService = usersService;
    }

    public void execute(Users users) {
        usersService.createUsers(users);
    }

}
