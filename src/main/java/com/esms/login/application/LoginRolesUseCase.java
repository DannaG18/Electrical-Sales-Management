package com.esms.login.application;

import com.esms.login.domain.service.LoginService;
import java.util.Optional;

public class LoginRolesUseCase {
    private final LoginService loginService;

    public LoginRolesUseCase(LoginService loginService) {
        this.loginService = loginService;
    }

    public Optional<String> getRoleName(int userId) {
        return loginService.getRoleName(userId);
    }
}
