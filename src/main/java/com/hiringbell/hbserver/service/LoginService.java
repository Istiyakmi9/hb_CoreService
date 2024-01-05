package com.hiringbell.hbserver.service;

import com.hiringbell.hbserver.entity.Login;

public interface LoginService {
    String authenticateUserService(Login login) throws Exception;
}

