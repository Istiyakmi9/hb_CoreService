package com.bot.coreservice.contracts;

import com.bot.coreservice.entity.JobType;
import com.bot.coreservice.entity.UserPosts;
import com.bot.coreservice.model.FileDetail;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

public interface IUserPostsService {

    public String addUserPostService(UserPosts userPost);
    public String updateUserPostService(UserPosts userPost, long userPostId) throws Exception;
    public List<UserPosts> getAllUserPosts();
    public Map<String, Object> getUserPostByUserPostIdService(long userPostId) throws Exception;
    public String deleteUserPostByUserPostIdService(long userPostId);
    public List<UserPosts> uploadUserPostsService(String userPost, Flux<FilePart> postImages) throws Exception;
    public List<UserPosts> updateUserPostsService(String userPost, Flux<FilePart> postImages) throws Exception;
    public List<FileDetail> deleteImagesService(Long userPostId, int fileDetailId) throws Exception;
    public  List<JobType> getAllJobTypeService();
}