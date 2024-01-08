package com.hiringbell.hbserver.service;

import com.hiringbell.hbserver.entity.Login;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    String authenticateUserService(Login login) throws Exception;
}
