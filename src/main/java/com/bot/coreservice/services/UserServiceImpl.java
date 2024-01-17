package com.bot.coreservice.services;

import com.bot.coreservice.Repository.*;
import com.bot.coreservice.entity.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bot.coreservice.db.LowLevelExecution;
import com.bot.coreservice.model.DbParameters;
import com.bot.coreservice.model.UserMaster;
import com.bot.coreservice.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    UserDetailRepository userDetailRepository;
    @Autowired
    UserMedicalDetailRepository userMedicalDetailRepository;
    @Autowired
    UserInterestsRepository userInterestsRepository;
    @Autowired
    LowLevelExecution lowLevelExecution;
    @Autowired
    ObjectMapper objectMapper;

    @Transactional(rollbackFor = Exception.class)
    public String addUserService(UserMaster userMaster) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        User user = new User();
        var lastUserId = this.userRepository.getLastUserId();
        if (lastUserId == null){
            user.setUserId(1L);
        }else {
            user.setUserId(lastUserId.getUserId()+1);
        }
        user.setFirstName(userMaster.getFirstName());
        user.setLastName(userMaster.getLastName());
        user.setFatherName(userMaster.getFatherName());
        user.setMotherName(userMaster.getMotherName());
        user.setEmail(userMaster.getEmail());
        user.setMobile(userMaster.getMobile());
        user.setAlternateNumber(userMaster.getAlternateNumber());
        user.setAddress(userMaster.getAddress());
        user.setCity(userMaster.getCity());
        user.setState(userMaster.getState());
        user.setCountry(userMaster.getCountry());
        user.setRoleId(userMaster.getRoleId());
        user.setDesignationId(userMaster.getDesignationId());
        user.setPinCode(userMaster.getPinCode());
        user.setReporteeId(userMaster.getReporteeId());
        user.setActive(true);
        user.setFriends("[]");
        user.setFollowers("[]");
        user.setCreatedOn(currentDate);
        userRepository.save(user);

        Login loginDetail = new Login();
        var lastLoginRecord = this.loginRepository.getLastLoginRecord();
        if (lastLoginRecord == null){
            loginDetail.setLoginId(1L);
        }else {
            loginDetail.setLoginId(lastLoginRecord.getLoginId()+1);
        }
        loginDetail.setUserId(user.getUserId());
        loginDetail.setEmail(userMaster.getEmail());
        loginDetail.setMobile(userMaster.getMobile());
        loginDetail.setPassword("emp123");
        loginDetail.setRoleId(userMaster.getRoleId());
        loginDetail.setActive(true);
        loginDetail.setCreatedBy(1L);
        loginDetail.setCreatedOn(currentDate);
        this.loginRepository.save(loginDetail);

        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(user.getUserId());
        userDetail.setPan(userMaster.getPan());
        userDetail.setAadhar(userMaster.getAadhar());
        userDetail.setPassportNumber(userMaster.getPassportNumber());
        userDetail.setBankName(userMaster.getBankName());
        userDetail.setAccountNo(userMaster.getAccountNo());
        userDetail.setBranch(userMaster.getBranch());
        userDetail.setIfscCode(userMaster.getIfscCode());
        userDetail.setJobTypeId(userMaster.getJobTypeId());
        userDetail.setExperienceInMonths(userMaster.getExperienceInMonths());
        userDetail.setLastCompanyName(userMaster.getLastCompanyName());
        userDetail.setLastWorkingDate(utilDate);
        userDetail.setDesignation(userMaster.getDesignation());
        userDetail.setSalary(userMaster.getSalary());
        userDetail.setExpectedSalary(userMaster.getExpectedSalary());
        userDetail.setCreatedBy(1L);
        userDetail.setCreatedOn(currentDate);
        userDetailRepository.save(userDetail);

        UserMedicalDetail userMedicalDetail = new UserMedicalDetail();
        var lastUserMedicalDetailRecord = this.userMedicalDetailRepository.getLastUserMedicalDetailRecord();
        if (lastUserMedicalDetailRecord == null){
            userMedicalDetail.setUserMedicalDetailId(1L);
        }else {
            userMedicalDetail.setUserMedicalDetailId(lastUserMedicalDetailRecord.getUserMedicalDetailId()+1);
        }
        userMedicalDetail.setUserId(user.getUserId());
        userMedicalDetail.setMedicalConsultancyId(userMaster.getMedicalConsultancyId());
        userMedicalDetail.setConsultedBy(userMaster.getConsultedBy());
        userMedicalDetail.setConsultedOn(utilDate);
        userMedicalDetail.setReferenceId(userMaster.getReferenceId());
        userMedicalDetail.setReportId(userMaster.getReportId());
        userMedicalDetail.setReportPath(userMaster.getReportPath());
        userMedicalDetail.setCreatedBy(1L);
        userMedicalDetail.setCreatedOn(currentDate);
        userMedicalDetailRepository.save(userMedicalDetail);

        return "New User has been added in User and Login table";
    }


    @Transactional(rollbackFor = Exception.class)
    public String updateUserService(UserMaster userMaster, long userId) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        Optional<User> result = this.userRepository.findById(userId);
        if (result.isEmpty())
            throw new Exception("user record not found");

        User existingUser = result.get();
        existingUser.setFirstName(userMaster.getFirstName());
        existingUser.setLastName(userMaster.getLastName());
        existingUser.setFatherName(userMaster.getFatherName());
        existingUser.setMotherName(userMaster.getMotherName());
        existingUser.setEmail(userMaster.getEmail());
        existingUser.setMobile(userMaster.getMobile());
        existingUser.setAlternateNumber(userMaster.getAlternateNumber());
        existingUser.setAddress(userMaster.getAddress());
        existingUser.setCity(userMaster.getCity());
        existingUser.setState(userMaster.getState());
        existingUser.setCountry(userMaster.getCountry());
        existingUser.setRoleId(userMaster.getRoleId());
        existingUser.setDesignationId(userMaster.getDesignationId());
        existingUser.setPinCode(userMaster.getPinCode());
        existingUser.setReporteeId(userMaster.getReporteeId());
        existingUser.setUpdatedBy(1L);
        existingUser.setUpdatedOn(currentDate);

        userRepository.save(existingUser);

        Login login;
        Optional<Login> loginResult = Optional.ofNullable(this.loginRepository.getLoginByUserId(userId));
        if (loginResult.isEmpty()){
            throw new Exception("Fail to get login, please contact to admin");
        }
        login = loginResult.get();
        login.setEmail(userMaster.getEmail());
        login.setMobile(userMaster.getMobile());
        login.setUpdatedBy(1L);
        login.setUpdatedOn(currentDate);
        Login loginData = this.loginRepository.save(login);
        if (loginData == null){
            throw new Exception("fail to update login. please contact to admin");
        }

        UserDetail userDetail;
        Optional<UserDetail> userDetailResult = Optional.ofNullable(this.userDetailRepository.getUserDetailByUserId(userId));
        if (userDetailResult.isEmpty())
            throw new Exception("UserDetail not found");

        userDetail = userDetailResult.get();
        userDetail.setPan(userMaster.getPan());
        userDetail.setAadhar(userMaster.getAadhar());
        userDetail.setPassportNumber(userMaster.getPassportNumber());
        userDetail.setBankName(userMaster.getBankName());
        userDetail.setAccountNo(userMaster.getAccountNo());
        userDetail.setBranch(userMaster.getBranch());
        userDetail.setBranch(userMaster.getIfscCode());
        userDetail.setJobTypeId(userMaster.getJobTypeId());
        userDetail.setExperienceInMonths(userMaster.getExperienceInMonths());
        userDetail.setLastCompanyName(userMaster.getLastCompanyName());
        userDetail.setLastWorkingDate(utilDate);
        userDetail.setDesignation(userMaster.getDesignation());
        userDetail.setSalary(userMaster.getSalary());
        userDetail.setExpectedSalary(userMaster.getExpectedSalary());
        userDetail.setExpectedDesignation(userMaster.getExpectedDesignation());
        userDetail.setUpdatedBy(1L);
        userDetail.setUpdatedOn(currentDate);
        UserDetail userDetailData = userDetailRepository.save(userDetail);
        if (userDetail == null){
            throw new Exception("fail to update UserDetail");
        }
        UserMedicalDetail userMedicalDetail;
        UserMedicalDetail userMedicalDetailResult = this.userMedicalDetailRepository.getUserMedicalDetailByUserId(userId);
        if (userMedicalDetailResult == null){
            throw new Exception("UserMedicalDetail not found");
        }
        userMedicalDetailResult.setMedicalConsultancyId(userMaster.getMedicalConsultancyId());
        userMedicalDetailResult.setConsultedBy(userMaster.getConsultedBy());
        userMedicalDetailResult.setConsultedOn(utilDate);
        userMedicalDetailResult.setReferenceId(userMaster.getReferenceId());
        userMedicalDetailResult.setReportId(userMaster.getReportId());
        userMedicalDetailResult.setReportPath(userMaster.getReportPath());
        userMedicalDetailResult.setUpdatedBy(1L);
        userMedicalDetailResult.setUpdatedOn(currentDate);
        UserMedicalDetail userMedicalDetailData = this.userMedicalDetailRepository.save(userMedicalDetailResult);
        return "User has been updated";
    }


    public ArrayList<User> getAllUserService() {
        List<User> result = this.userRepository.findAll();
        return (ArrayList<User>) result;
    }

    public UserMaster getUserByUserIdService(long userId) {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_UserId", userId, Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_User_getByUserId", dbParameters);
        var data =  objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<UserMaster>>() {
        });
        if (data != null)
            return data.get(0);
        else
            return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public String deleteUserByUserIdService(long userId) throws Exception {
        Optional<User> result = this.userRepository.findById(userId);
        if (result.isEmpty()) {
            throw new Exception("user record not found");
        }
        User existingUser = result.get();
        existingUser.setActive(false);
        this.userRepository.save(existingUser);

        Login loginResult = this.loginRepository.getLoginByUserId(userId);
        if (loginResult == null){
            throw new Exception("login record not found");
        }
        loginResult.setActive (false);
        this.loginRepository.save(loginResult);
        return "User data is De-active";
    }

    public ArrayList<UserInterests> updateUserInterestService(List<Integer> userInterest, Integer userId) {
        ArrayList<UserInterests> userInterestList = new ArrayList<UserInterests>();
        for (var item: userInterest){
            userInterestList.add(new UserInterests(userId, item));
        }
        userInterestsRepository.saveAll(userInterestList);
        return userInterestList;
    }
}
