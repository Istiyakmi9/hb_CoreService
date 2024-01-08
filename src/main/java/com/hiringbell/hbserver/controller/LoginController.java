package com.hiringbell.hbserver.controller;

import com.hiringbell.hbserver.entity.Login;
import com.hiringbell.hbserver.model.ApiResponse;
import com.hiringbell.hbserver.serviceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    LoginServiceImpl loginService;

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> authenticate(@RequestBody Login login) throws Exception {
        var result = loginService.authenticateUserService(login);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
