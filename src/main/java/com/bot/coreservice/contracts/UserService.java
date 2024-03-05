package com.bot.coreservice.contracts;

import com.bot.coreservice.entity.User;
import com.bot.coreservice.entity.UserInterests;
import com.bot.coreservice.model.FilterModel;
import com.bot.coreservice.model.UserMaster;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface UserService {

    public String addUserService(UserMaster userMaster) throws Exception;
    public String updateUserService(UserMaster userMaster, long userId) throws Exception;
    public ArrayList<User> getAllUserService();
    public UserMaster getUserByUserIdService(long userId);
    public String deleteUserByUserIdService(long userId) throws Exception;
    public ArrayList<UserInterests> updateUserInterestService(List<Integer> userInterest) throws Exception;
    public Map<String, Object> getJobandLocationService(int categoryId);
    public  String addJobandLocationService(UserMaster user) throws Exception;
    public List<User> getFriendsService();
    public List<User> filterFriendService(FilterModel filterModel);
    public String manageFriendService(long userId) throws Exception;
}
