package com.hiringbell.hbserver.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiringbell.hbserver.Repository.LoginRepository;
import com.hiringbell.hbserver.entity.Login;
import com.hiringbell.hbserver.jwtconfig.JwtGateway;
import com.hiringbell.hbserver.model.ApplicationConstant;
import com.hiringbell.hbserver.model.JwtTokenModel;
import com.hiringbell.hbserver.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginRepository loginRepository;


    public String authenticateUserService(Login login) throws Exception {
        try {
            validateLoginDetail(login);
            Login loginDetail = null;
            loginDetail = loginRepository.getLoginByEmailOrMobile(login.getMobile(), login.getEmail());

            if (loginDetail == null)
                throw new Exception("Login detail not found");

            validateCredential(loginDetail, login);
            ObjectMapper mapper = new ObjectMapper();
            String loginDetailJson = mapper.writeValueAsString(loginDetail);
            JwtTokenModel jwtTokenModel = new JwtTokenModel();
            jwtTokenModel.setUserDetail(loginDetailJson);
            jwtTokenModel.setUserId(loginDetail.getEmployeeId());
            jwtTokenModel.setEmail(loginDetail.getEmail());
            jwtTokenModel.setCompanyCode("");
            switch (loginDetail.getRoleId()){
                case 1:
                    jwtTokenModel.setRole(ApplicationConstant.Admin);
                    break;
                case 2:
                    jwtTokenModel.setRole(ApplicationConstant.Employee);
                    break;
                case 3:
                    jwtTokenModel.setRole(ApplicationConstant.Client);
                    break;
            }
            JwtGateway jwtGateway = JwtGateway.getJwtGateway();
            String result = jwtGateway.generateJwtToken(jwtTokenModel);
            return result;
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
