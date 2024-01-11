package com.bot.coreservice.serviceImpl;

import com.bot.coreservice.Repository.UserPostsRepository;
import com.bot.coreservice.entity.UserPosts;
import com.bot.coreservice.service.UserPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserPostsServiceImpl implements UserPostsService {

    @Autowired
    UserPostsRepository userPostsRepository;


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
        existingUserPost.setCatagoryTypeId(1);
        existingUserPost.setPostInternalDetailId(1);
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
}
