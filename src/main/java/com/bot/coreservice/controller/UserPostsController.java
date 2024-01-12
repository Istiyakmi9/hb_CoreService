package com.bot.coreservice.controller;

import com.bot.coreservice.entity.UserPosts;
import com.bot.coreservice.model.ApiResponse;
import com.bot.coreservice.serviceImpl.UserPostsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hb/api/userposts")
public class UserPostsController {

    @Autowired
    UserPostsServiceImpl userPostsServiceImpl;

    @PostMapping("/addUserPost")
    public ResponseEntity<ApiResponse> addUserPost(@RequestBody UserPosts userPost) {
        var result = this.userPostsServiceImpl.addUserPostService(userPost);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/updateUserPost/{userPostId}")
    public ResponseEntity<ApiResponse> updateUserPost(@RequestBody UserPosts userPost, @PathVariable("userPostId") long userPostId) throws Exception {
        var result = this.userPostsServiceImpl.updateUserPostService(userPost, userPostId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }


    @GetMapping("/getAllUserPosts")
    public ResponseEntity<ApiResponse> getAllUserPosts(){
        var result = this.userPostsServiceImpl.getAllUserPosts();
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getUserPostByUserPostId/{userPostId}")
    public ResponseEntity<ApiResponse> getUserPostByUserPostId(@PathVariable("userPostId") long userPostId){
        var result = this.userPostsServiceImpl.getUserPostByUserPostIdService(userPostId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("/deleteUserPostByUserPostId/{userPostId}")
    public ResponseEntity<ApiResponse> deleteUserPostByUserPostId(@PathVariable("userPostId") long userPostId){
        var result = this.userPostsServiceImpl.deleteUserPostByUserPostIdService(userPostId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }


}
