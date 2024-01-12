package com.bot.coreservice.controller;

import com.bot.coreservice.entity.Login;
import com.bot.coreservice.model.ApiResponse;
import com.bot.coreservice.services.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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
