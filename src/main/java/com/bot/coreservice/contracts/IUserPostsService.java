package com.bot.coreservice.contracts;

import com.bot.coreservice.entity.UserPosts;
import com.bot.coreservice.model.UploadRequestFormData;
import com.bot.coreservice.model.UserPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Optional;

public interface IUserPostsService {

    public String addUserPostService(UserPosts userPost);
    public String updateUserPostService(UserPosts userPost, long userPostId) throws Exception;
    public ArrayList<UserPosts> getAllUserPosts();
    public Optional<UserPosts> getUserPostByUserPostIdService(long userPostId);
    public String deleteUserPostByUserPostIdService(long userPostId);
    public String uploadUserPostsService(String userPost, FilePart postImages) throws JsonProcessingException;
}