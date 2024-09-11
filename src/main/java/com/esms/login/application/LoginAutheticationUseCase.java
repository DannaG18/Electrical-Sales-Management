package com.esms.login.application;

import java.util.Optional;

import com.esms.login.domain.entity.LoginUsers;
import com.esms.login.domain.service.LoginService;

public class LoginAutheticationUseCase {

    private final LoginService loginService;

    public LoginAutheticationUseCase(LoginService loginService) {
        this.loginService = loginService;
    }

    public Optional<LoginUsers> login(String username, String password) {
        return loginService.loginAuthenticateUser(username, password); 
    } 
}
