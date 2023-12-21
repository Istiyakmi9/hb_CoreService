package com.hiringbell.hbserver.service;

import com.hiringbell.hbserver.Repository.LoginRepository;
import com.hiringbell.hbserver.entity.Login;
import com.hiringbell.hbserver.serviceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImpl {
    @Autowired
    LoginRepository loginRepository;
    public String authenticateUserService(Login login) throws Exception {
        try {
            validateLoginDetail(login);
            Login loginDetail = null;
            if (login.getEmail() != null) {
                loginDetail = loginRepository.getLoginByEmail(login.getEmail());
            } else {
                loginDetail = loginRepository.getLoginByMobile(login.getMobile());
            }

            if (loginDetail == null)
                throw new Exception("Login detail not found");

            validateCredential(loginDetail, login);

            return "Login successfully";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void validateCredential(Login login, Login request) throws Exception {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String password = encoder.encode(request.getPassword());
//        if(!encoder.matches(password, login.getPassword())) {
//            user = userService.getByUserMobileService(request.getMobile());
//        } else {
//            throw new Exception("Invalid username or password.");
//        }
        if (!login.getPassword().equals(request.getPassword()))
            throw new Exception("Password is not matched");
    }

    private void validateLoginDetail(Login login) throws Exception {
        if (login.getPassword() == null || login.getPassword().isEmpty())
            throw new Exception("Password is required");

        if ((login.getEmail() == null || login.getEmail().isEmpty()) && (login.getMobile() == null || login.getMobile().isEmpty()))
            throw new Exception("Email or Mobile number is required");
    }
}
