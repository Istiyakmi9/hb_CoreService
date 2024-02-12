package com.bot.coreservice.controller;

import com.bot.coreservice.model.ApiResponse;
import com.bot.coreservice.model.UserMaster;
import com.bot.coreservice.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;


@RestController
@RequestMapping("/hb/api/core/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse> addUser(@RequestBody UserMaster userMaster) throws Exception {
        var result = this.userServiceImpl.addUserService(userMaster);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserMaster userMaster,
                                                  @PathVariable("userId") long userId) throws Exception {
        var result = this.userServiceImpl.updateUserService(userMaster, userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<ApiResponse> getAllUser(){
        var result = this.userServiceImpl.getAllUserService();
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getUserByUserId/{employeeId}")
    public ResponseEntity<ApiResponse> getUserByUserId( @PathVariable("userId") long userId) throws Exception {
        var result = this.userServiceImpl.getUserByUserIdService(userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("/deleteUserByUserId/{employeeId}")
    public ResponseEntity<ApiResponse> deleteUserByUserId( @PathVariable("userId") long userId) throws Exception {
        var result = this.userServiceImpl.deleteUserByUserIdService(userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("/updateUserInterest")
    public ResponseEntity<ApiResponse> updateUserInterest(@RequestBody List<Integer> userInterest) throws Exception {
        var result = this.userServiceImpl.updateUserInterestService(userInterest);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
