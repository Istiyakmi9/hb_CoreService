package com.bot.coreservice.contracts;

import com.bot.coreservice.entity.JobType;
import com.bot.coreservice.entity.UserPosts;
import com.bot.coreservice.model.FileDetail;
import com.bot.coreservice.model.UserPostRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IUserPostsService {

    public String addUserPostService(UserPosts userPost);
    public String updateUserPostService(UserPosts userPost, long userPostId) throws Exception;
    public List<UserPosts> getHomePageService(int page, int pageSize);
    public List<UserPosts> getOwnPageService(int page, int pageSize);
    public List<UserPosts> getAppliedJobPageService(int page, int pageSize);
    public List<UserPosts> getSavedJobPageService(int page, int pageSize);
    public Map<String, Object> getUserPostByUserPostIdService(long userPostId) throws Exception;
    public List<UserPosts> deleteUserPostByUserPostIdService(long userPostId) throws Exception;
    public List<UserPosts> uploadUserPostsService(String userPost, MultipartFile[] postImages) throws Exception;
    public void saveUserPostedData(String userPost, MultipartFile[] postImages) throws Exception;
    public List<UserPosts> updateUserPostsService(String userPost, MultipartFile[] postImages) throws Exception;
    public UserPostRequest updateUserPostsServiceMobile(String userPost, MultipartFile[] postImages) throws Exception;
    public List<FileDetail> deleteImagesService(Long userPostId, int fileDetailId) throws Exception;
    public  List<JobType> getAllJobTypeService();
    public List<UserPosts> getPostByUserIdService(Long userId) throws Exception;
    public UserPostRequest getPostByPostIdService(Long postId) throws Exception;
    public String addLikedPostService(UserPosts userPost) throws Exception;
    public String deleteLikedPostService(long userPostId) throws Exception;
    public String addAppliedPostService(UserPosts userPost) throws Exception;
    public String addSavedPostService(UserPosts userPost) throws Exception;
}