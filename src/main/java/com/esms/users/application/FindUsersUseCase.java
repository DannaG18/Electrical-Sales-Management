package com.esms.users.application;

import java.util.Optional;

import com.esms.users.domain.entity.Users;
import com.esms.users.domain.service.UsersService;

public class FindUsersUseCase {
    private UsersService usersService;

    public FindUsersUseCase(UsersService usersService) {
        this.usersService = usersService;
    }

    public Optional<Users> execute(int id) {
        return usersService.findUsers(id);
    }
}
