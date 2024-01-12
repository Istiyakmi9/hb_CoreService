package com.bot.coreservice.services;

import com.bot.coreservice.Repository.JobRequirementRepository;
import com.bot.coreservice.Repository.UserPostsRepository;
import com.bot.coreservice.entity.JobRequirement;
import com.bot.coreservice.entity.UserPosts;
import com.bot.coreservice.model.UploadRequestFormData;
import com.bot.coreservice.model.UserPostRequest;
import com.bot.coreservice.contracts.IUserPostsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserPostsServiceImpl implements IUserPostsService {

    @Autowired
    UserPostsRepository userPostsRepository;

    @Autowired
    JobRequirementRepository jobRequirementRepository;
    @Autowired
    ObjectMapper objectMapper;

    public String addUserPostService(UserPosts userPost) {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        var lastUserPostRecord = this.userPostsRepository.getLastUserPostRecord();
        if (lastUserPostRecord == null){
            userPost.setUserPostId(1L);
        }else {
            userPost.setUserPostId(lastUserPostRecord.getUserPostId()+1);
        }
        userPost.setPostedOn(currentDate);
        this.userPostsRepository.save(userPost);

        return "Posted successfully";


    }

    public String updateUserPostService(UserPosts userPost, long userPostId) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        Optional<UserPosts> result = this.userPostsRepository.findById(userPostId);
        if (result.isEmpty()){
            throw new Exception("No user post found");
        }
        UserPosts existingUserPost = result.get();
        existingUserPost.setShortDescription(userPost.getShortDescription());
        existingUserPost.setCompleteDescription(userPost.getCompleteDescription());
        existingUserPost.setCatagoryTypeId(1L);
        existingUserPost.setJobRequirementId(1L);
        existingUserPost.setUpdatedOn(currentDate);
        this.userPostsRepository.save(existingUserPost);
        return "User post has been updated";
    }

    public ArrayList<UserPosts> getAllUserPosts() {
        List result = this.userPostsRepository.findAll();
        return  (ArrayList<UserPosts>) result;
    }

    public Optional<UserPosts> getUserPostByUserPostIdService(long userPostId) {
        Optional<UserPosts> result = this.userPostsRepository.findById(userPostId);
        return result;
    }

    public String deleteUserPostByUserPostIdService(long userPostId) {
        this.userPostsRepository.deleteById(userPostId);
        return "User post has been deleted";
    }

    @Transactional(rollbackFor = Exception.class)
    public String uploadUserPostsService(String userPost, FilePart postImages) throws JsonProcessingException{
        UserPostRequest userPostRequest = objectMapper.readValue(userPost, UserPostRequest.class);
        UserPosts userPosts = objectMapper.convertValue(userPostRequest, UserPosts.class);
        JobRequirement jobRequirement = objectMapper.convertValue(userPostRequest, JobRequirement.class);
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        var lastJobRequirementRecord = this.jobRequirementRepository.getLastJobRequirementRecord();
        if (lastJobRequirementRecord == null){
            jobRequirement.setJobRequirementId(1L);
        }else{
            jobRequirement.setJobRequirementId(lastJobRequirementRecord.getJobRequirementId()+1);
        }

        jobRequirement.setRequiredShortDesc(userPostRequest.getShortDescription());
        jobRequirement.setCreatedBy(1L);
        jobRequirement.setJobTypeId(userPosts.getCatagoryTypeId());
        jobRequirement.setCreatedOn(currentDate);
        this.jobRequirementRepository.save(jobRequirement);

        var lastUserPostRecord = this.userPostsRepository.getLastUserPostRecord();
        if (lastUserPostRecord == null){
            userPosts.setUserPostId(1L);
        }else{
            userPosts.setUserPostId(lastUserPostRecord.getUserPostId()+1);
        }
        userPosts.setJobRequirementId(jobRequirement.getJobRequirementId());
        userPosts.setPostedBy(1L);
        userPosts.setPostedOn(currentDate);

        return "New userPost and jobRequirement has been added";
    }

    @Override
    public String updateUserPostsService(String userPost, FilePart postImages) throws Exception {

        UserPostRequest userPostRequest = objectMapper.readValue(userPost, UserPostRequest.class);
        UserPosts userPosts = objectMapper.convertValue(userPostRequest, UserPosts.class);
        JobRequirement jobRequirement = objectMapper.convertValue(userPostRequest, JobRequirement.class);
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        Optional<JobRequirement> result = this.jobRequirementRepository.findById(jobRequirement.getJobRequirementId());
        if (result.isEmpty())
            throw new Exception("JobRequirement record not found");
        JobRequirement existingjobRequirement = result.get();
//        existingjobRequirement.setRequiredShortDesc();



        return null;
    }


}
