package com.bot.coreservice.controller;

import com.bot.coreservice.entity.UserPosts;
import com.bot.coreservice.model.ApiResponse;
import com.bot.coreservice.contracts.IUserPostsService;
import com.bot.coreservice.model.UploadRequestFormData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;


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


    @GetMapping("getAllUserPosts")
    public ResponseEntity<ApiResponse> getAllUserPosts() {
        var result = this.userPostsService.getAllUserPosts();
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getUserPostByUserPostId/{userPostId}")
    public ResponseEntity<ApiResponse> getUserPostByUserPostId(@PathVariable("userPostId") long userPostId) throws Exception {
        var result = this.userPostsService.getUserPostByUserPostIdService(userPostId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("deleteUserPostByUserPostId/{userPostId}")
    public ResponseEntity<ApiResponse> deleteUserPostByUserPostId(@PathVariable("userPostId") long userPostId) {
        var result = this.userPostsService.deleteUserPostByUserPostIdService(userPostId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("uploadUserPosts")
    public ResponseEntity<ApiResponse> uploadUserPosts(
            @RequestPart("userPost") String userPost,
            @RequestPart(value = "postImages", required = false)Flux<FilePart> postImages) throws Exception {
        var result = userPostsService.uploadUserPostsService(userPost, postImages);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("updateUserPosts")
    public ResponseEntity<ApiResponse> updateUserPosts(
            @RequestPart("userPost") String userPost,
            @RequestPart(value = "postImages", required = false) Flux<FilePart> postImages) throws Exception {
        var result = userPostsService.updateUserPostsService(userPost, postImages);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("deleteImages/{userPostId}/{fileDetailId}")
    public ResponseEntity<ApiResponse> deleteImages(@PathVariable("userPostId") Long userPostId, @PathVariable("fileDetailId") int fileDetailId) throws Exception {
        var result = this.userPostsService.deleteImagesService(userPostId, fileDetailId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}