package com.hiringbell.hbserver.serviceImpl;

import com.hiringbell.hbserver.entity.Login;

public interface LoginServiceImpl {
    String authenticateUserService(Login login) throws Exception;
}
