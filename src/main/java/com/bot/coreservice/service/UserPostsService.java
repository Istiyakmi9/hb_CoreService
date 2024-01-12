package com.bot.coreservice.service;

import com.bot.coreservice.entity.UserPosts;

import java.util.ArrayList;
import java.util.Optional;

public interface UserPostsService {

    public String addUserPostService(UserPosts userPost);
    public String updateUserPostService(UserPosts userPost, long userPostId) throws Exception;
    public ArrayList<UserPosts> getAllUserPosts();
    public Optional<UserPosts> getUserPostByUserPostIdService(long userPostId);
    public String deleteUserPostByUserPostIdService(long userPostId);


}
