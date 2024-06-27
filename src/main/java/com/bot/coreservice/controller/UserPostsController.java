package com.bot.coreservice.controller;

import com.bot.coreservice.contracts.IUserPostsService;
import com.bot.coreservice.entity.UserPosts;
import com.bot.coreservice.model.ApiResponse;
import com.bot.coreservice.model.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/hb/api/core/userposts/")
public class UserPostsController {
    @Autowired
    IUserPostsService userPostsService;

    @PostMapping("addUserPost")
    public ResponseEntity<ApiResponse> addUserPost(@RequestBody UserPosts userPost) {
        var result = this.userPostsService.addUserPostService(userPost);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("updateUserPost/{userPostId}")
    public ResponseEntity<ApiResponse> updateUserPost(@RequestBody UserPosts userPost, @PathVariable("userPostId") long userPostId) throws Exception {
        var result = this.userPostsService.updateUserPostService(userPost, userPostId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getHomePage/{page}")
    public ResponseEntity<ApiResponse> getHomePage(@PathVariable int page) throws Exception {
        var result = this.userPostsService.getHomePageService(page, 10);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getOwnPosts/{page}")
    public ResponseEntity<ApiResponse> getOwnPosts(@PathVariable int page) throws Exception {
        var result = this.userPostsService.getOwnPageService(page, 10);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getUserPostByUserPostId/{userPostId}")
    public ResponseEntity<ApiResponse> getUserPostByUserPostId(@PathVariable("userPostId") long userPostId) throws Exception {
        var result = this.userPostsService.getUserPostByUserPostIdService(userPostId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("deleteUserPostByUserPostId/{userPostId}")
    public ResponseEntity<ApiResponse> deleteUserPostByUserPostId(@PathVariable("userPostId") long userPostId) throws Exception {
        var result = this.userPostsService.deleteUserPostByUserPostIdService(userPostId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("uploadUserPosts")
    public ResponseEntity<ApiResponse> uploadUserPosts(
            @RequestPart("userPost") String userPost,
            @RequestPart(value = "postImages", required = false) MultipartFile[] postImages) throws Exception {
        var result = userPostsService.uploadUserPostsService(userPost, postImages);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("uploadUserPostsMobile")
    public ResponseEntity<ApiResponse> uploadUserPostsMobile(
            @RequestPart("userPost") String userPost,
            @RequestPart(value = "postImages", required = false) MultipartFile[] postImages) throws Exception {
        userPostsService.saveUserPostedData(userPost, postImages);
        return ResponseEntity.ok(ApiResponse.Ok(Constants.Success));
    }

    @PostMapping("updateUserPosts")
    public ResponseEntity<ApiResponse> updateUserPosts(
            @RequestPart("userPost") String userPost,
            @RequestPart(value = "postImages", required = false) MultipartFile[] postImages) throws Exception {
        var result = userPostsService.updateUserPostsService(userPost, postImages);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
    @PostMapping("updateUserPostsMobile")
    public ResponseEntity<ApiResponse> updateUserPostsMobile(
            @RequestPart("userPost") String userPost,
            @RequestPart(value = "postImages", required = false) MultipartFile[] postImages) throws Exception {
        var result = userPostsService.updateUserPostsServiceMobile(userPost, postImages);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("deleteImages/{userPostId}/{fileDetailId}")
    public ResponseEntity<ApiResponse> deleteImages(@PathVariable("userPostId") Long userPostId, @PathVariable("fileDetailId") int fileDetailId) throws Exception {
        var result = this.userPostsService.deleteImagesService(userPostId, fileDetailId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getAllJobType")
    public ResponseEntity<ApiResponse> getAllJobType() {
        var result = this.userPostsService.getAllJobTypeService();
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getPostByUserId/{userId}")
    public ResponseEntity<ApiResponse> getPostByUserId(@PathVariable("userId") Long userId) throws Exception {
        var result = this.userPostsService.getPostByUserIdService(userId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getPostByPostId/{postId}")
    public ResponseEntity<ApiResponse> getPostByPostId(@PathVariable("postId") Long postId) throws Exception {
        var result = this.userPostsService.getPostByPostIdService(postId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("addLikedPost")
    public ResponseEntity<ApiResponse> addLikedPost(@RequestBody UserPosts userPost) throws Exception {
        var result = this.userPostsService.addLikedPostService(userPost);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("deleteLikedPost/{userPostId}")
    public ResponseEntity<ApiResponse> deleteLikedPost(@PathVariable("userPostId") long userPostId) throws Exception {
        var result = this.userPostsService.deleteLikedPostService(userPostId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("addAppliedPost")
    public ResponseEntity<ApiResponse> addAppliedPost(@RequestBody UserPosts userPost) throws Exception {
        var result = this.userPostsService.addAppliedPostService(userPost);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}