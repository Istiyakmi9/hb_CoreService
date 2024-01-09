package com.hiringbell.hbserver.service;

import com.hiringbell.hbserver.entity.Login;
import com.hiringbell.hbserver.model.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    LoginResponse authenticateUserService(Login login) throws Exception;
}
