package com.bot.coreservice.contracts;

import com.bot.coreservice.entity.User;
import com.bot.coreservice.model.UserMaster;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;

public interface UserService {

    public String addUserService(UserMaster userMaster, ServerWebExchange exchange) throws Exception;
    public String updateUserService(UserMaster userMaster, long userId, ServerWebExchange exchange) throws Exception;
    public ArrayList<User> getAllUserService();
    public UserMaster getUserByUserIdService(long userId);
    public String deleteUserByUserIdService(long userId) throws Exception;

}
