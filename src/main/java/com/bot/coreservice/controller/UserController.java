package com.bot.coreservice.controller;

import com.bot.coreservice.entity.User;
import com.bot.coreservice.model.ApiResponse;
import com.bot.coreservice.model.FilterModel;
import com.bot.coreservice.model.UserMaster;
import com.bot.coreservice.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @GetMapping("/getUserByUserId/{userId}")
    public ResponseEntity<ApiResponse> getUserByUserId( @PathVariable("userId") long userId) throws Exception {
        var result = this.userServiceImpl.getUserByUserIdService(userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("/deleteUserByUserId/{userId}")
    public ResponseEntity<ApiResponse> deleteUserByUserId( @PathVariable("userId") long userId) throws Exception {
        var result = this.userServiceImpl.deleteUserByUserIdService(userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("/updateUserInterest")
    public ResponseEntity<ApiResponse> updateUserInterest(@RequestBody List<Integer> userInterest) throws Exception {
        var result = this.userServiceImpl.updateUserInterestService(userInterest);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getJobandLocation/{categoryId}")
    public ResponseEntity<ApiResponse> getJobandLocation(@PathVariable int categoryId) {
        var result = this.userServiceImpl.getJobandLocationService(categoryId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("/addJobandLocation")
    public ResponseEntity<ApiResponse> getJobandLocation(@RequestBody UserMaster user) throws Exception {
        var result = this.userServiceImpl.addJobandLocationService(user);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getFriends")
    public ResponseEntity<ApiResponse> getFriends()  {
        var result = this.userServiceImpl.getFriendsService();
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("/filterFriend")
    public  ResponseEntity<ApiResponse> filterFriend(@RequestBody FilterModel filterModel) {
        var result = userServiceImpl.filterFriendService(filterModel);
        return  ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/manageFriend/{userId}")
    public ResponseEntity<ApiResponse> manageFriend(@PathVariable long userId) throws Exception {
        var result = this.userServiceImpl.manageFriendService(userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("/addUserImage/{userId}")
    public ResponseEntity<ApiResponse> addUserImage(@RequestPart(value = "userimage") MultipartFile userImage,
                                                    @PathVariable Long userId) throws Exception {
        var result = this.userServiceImpl.addUserImageService(userImage, userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}