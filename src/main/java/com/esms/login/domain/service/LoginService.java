package com.esms.login.domain.service;

import java.util.Optional;

import com.esms.login.domain.entity.LoginUsers;

public interface LoginService {
    public Optional<LoginUsers> loginAuthenticateUser(String username, String password);
    public Optional<String> getRoleName(int userId);
}
