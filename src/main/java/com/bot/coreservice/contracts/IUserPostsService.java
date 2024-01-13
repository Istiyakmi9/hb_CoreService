package com.bot.coreservice.contracts;

import com.bot.coreservice.entity.UserPosts;
import com.bot.coreservice.model.UploadRequestFormData;
import com.bot.coreservice.model.UserPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUserPostsService {

    public String addUserPostService(UserPosts userPost);
    public String updateUserPostService(UserPosts userPost, long userPostId) throws Exception;
    public List<UserPosts> getAllUserPosts();
    public Map<String, Object> getUserPostByUserPostIdService(long userPostId) throws Exception;
    public String deleteUserPostByUserPostIdService(long userPostId);
    public String uploadUserPostsService(String userPost, Flux<FilePart> postImages) throws Exception;
    public String updateUserPostsService(String userPost, Flux<FilePart> postImages) throws Exception;
}