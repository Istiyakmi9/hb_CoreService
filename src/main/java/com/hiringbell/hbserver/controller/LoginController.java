package com.hiringbell.hbserver.controller;

import com.hiringbell.hbserver.entity.Login;
import com.hiringbell.hbserver.model.ApiResponse;
import com.hiringbell.hbserver.serviceImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hb/api")
public class LoginController {
    @Autowired
    LoginServiceImpl loginService;

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> authenticate(@RequestBody Login login) throws Exception {
        var result = loginService.authenticateUserService(login);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
