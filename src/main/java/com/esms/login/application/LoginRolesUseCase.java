package com.esms.login.application;

import com.esms.login.domain.service.LoginService;

public class LoginRolesUseCase {
    private final LoginService loginService;

    public LoginRolesUseCase(LoginService loginService) {
        this.loginService = loginService;
    }

    public boolean roles(int id) {
        return loginService.loginRoleUser(id);
    }
}
