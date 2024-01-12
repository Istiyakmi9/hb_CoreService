package com.bot.coreservice.contracts;

import com.bot.coreservice.entity.Login;
import com.bot.coreservice.model.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    LoginResponse authenticateUserService(Login login) throws Exception;
}
