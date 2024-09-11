package com.esms.users.application;

import com.esms.users.domain.service.UsersService;

public class DeleteUsersUseCase {
    private UsersService usersService;

    public DeleteUsersUseCase(UsersService usersService) {
        this.usersService = usersService;
    }

    public  void execute(int id) {
        usersService.deleteUsers(id);
    }

}
