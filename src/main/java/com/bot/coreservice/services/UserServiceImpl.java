package com.bot.coreservice.services;

import com.bot.coreservice.Repository.*;
import com.bot.coreservice.contracts.UserService;
import com.bot.coreservice.db.LowLevelExecution;
import com.bot.coreservice.entity.*;
import com.bot.coreservice.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.*;

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
    UserDocumentRepository userDocumentRepository;
    @Autowired
    LowLevelExecution lowLevelExecution;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    FileManager fileManager;
    @Autowired
    CurrentSession currentSession;


    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Transactional(rollbackFor = Exception.class)
    public String addUserService(UserMaster userMaster) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        User user = new User();
        var lastUserId = this.userRepository.getLastUserId();
        if (lastUserId == null) {
            user.setUserId(1L);
        } else {
            user.setUserId(lastUserId.getUserId() + 1);
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
        if (lastLoginRecord == null) {
            loginDetail.setLoginId(1L);
        } else {
            loginDetail.setLoginId(lastLoginRecord.getLoginId() + 1);
        }
        loginDetail.setUserId(user.getUserId());
        loginDetail.setEmail(userMaster.getEmail());
        loginDetail.setMobile(userMaster.getMobile());
        loginDetail.setPassword(userMaster.getPassword());
        loginDetail.setRoleId(userMaster.getRoleId());
        loginDetail.setActive(true);
        loginDetail.setCreatedBy(currentSession.getUser().getUserId());
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
        userDetail.setCreatedBy(currentSession.getUser().getUserId());
        userDetail.setCreatedOn(currentDate);
        userDetailRepository.save(userDetail);

        UserMedicalDetail userMedicalDetail = new UserMedicalDetail();
        var lastUserMedicalDetailRecord = this.userMedicalDetailRepository.getLastUserMedicalDetailRecord();
        if (lastUserMedicalDetailRecord == null) {
            userMedicalDetail.setUserMedicalDetailId(1L);
        } else {
            userMedicalDetail.setUserMedicalDetailId(lastUserMedicalDetailRecord.getUserMedicalDetailId() + 1);
        }
        userMedicalDetail.setUserId(user.getUserId());
        userMedicalDetail.setMedicalConsultancyId(userMaster.getMedicalConsultancyId());
        userMedicalDetail.setConsultedBy(userMaster.getConsultedBy());
        userMedicalDetail.setConsultedOn(utilDate);
        userMedicalDetail.setReferenceId(userMaster.getReferenceId());
        userMedicalDetail.setReportId(userMaster.getReportId());
        userMedicalDetail.setReportPath(userMaster.getReportPath());
        userMedicalDetail.setCreatedBy(currentSession.getUser().getUserId());
        userMedicalDetail.setCreatedOn(currentDate);
        userMedicalDetailRepository.save(userMedicalDetail);

        return "New User has been added in User and Login table";
    }

    @Transactional(rollbackFor = Exception.class)
    public String addUserServiceMobile(String userMaster,MultipartFile profileImage, MultipartFile[] userDocs) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        var lastUserId = this.userRepository.getLastUserId();
        User user =objectMapper.readValue(userMaster, User.class);
        if (lastUserId == null) {
            user.setUserId(1L);
        } else {
            user.setUserId(lastUserId.getUserId() + 1);
        }
        var filePath = UploadNewFile(profileImage, user.getUserId(), "profile_","profile");
        user.setImageURL(filePath);

        user.setActive(true);
        user.setFriends("[]");
        user.setFollowers("[]");
        user.setCreatedOn(currentDate);
        userRepository.save(user);

        UploadDocuments(userDocs, user);

        AddLoginDetails(user, currentDate);

        return "New User has been added in User and Login table";
    }

    private void UploadDocuments(MultipartFile[] userDocs, User user) throws Exception {
        if (userDocs == null) return;
        List<UserDocument> docs = new ArrayList<>();
        for (int i = 0; i < userDocs.length; i++) {
            var _file = userDocs[i];
            var fileName = _file.getName();
            var fileOrigName = _file.getOriginalFilename();
            var userDoc = new UserDocument();
            userDoc.setUserId(user.getUserId());
            userDoc.setDocName(user.getUserDocs().get(i).getDocName());
            var _filePath = UploadNewFile(userDocs[i], user.getUserId(), "userDoc_","userDoc");
            userDoc.setDocPath(_filePath);
            docs.add(userDoc);
        }
        userDocumentRepository.saveAll(docs);
    }

    private String UploadNewFile(MultipartFile newFile, long userId, String folderName,String fileType) throws Exception{
        if(newFile == null) return "";
        String filepath = "";
        try {
//            filepath = fileManager.uploadFile(newFile, userId, "profile_" + new Date().getTime(), "profile");
            filepath = fileManager.uploadFile(newFile, userId, folderName + new Date().getTime(), fileType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  filepath;
    }

    private void DeleteOldFile(String filePath) throws Exception {
        if(filePath == null || filePath.isEmpty()) return;
        fileManager.DeleteFile(filePath);
    }

    private String UpdateFile(MultipartFile newFile, String filePath ,long userId, String folderName,String fileType) throws Exception {
        DeleteOldFile(filePath);
        return UploadNewFile(newFile, userId, folderName,fileType);
    }

    private void AddLoginDetails(User user,  Timestamp currentDate) {
        Login loginDetail = new Login();
        var lastLoginRecord = this.loginRepository.getLastLoginRecord();
        if (lastLoginRecord == null) {
            loginDetail.setLoginId(1L);
        } else {
            loginDetail.setLoginId(lastLoginRecord.getLoginId() + 1);
        }
        loginDetail.setUserId(user.getUserId());
        loginDetail.setEmail(user.getEmail());
        loginDetail.setMobile(user.getMobile());
        loginDetail.setPassword(user.getPassword());
        loginDetail.setRoleId(user.getRoleId());
        loginDetail.setActive(true);
        loginDetail.setCreatedBy(currentSession.getUser().getUserId());
        loginDetail.setCreatedOn(currentDate);
        this.loginRepository.save(loginDetail);
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
        existingUser.setUpdatedBy(currentSession.getUser().getUserId());
        existingUser.setUpdatedOn(currentDate);

        userRepository.save(existingUser);

        Login login;
        Optional<Login> loginResult = Optional.ofNullable(this.loginRepository.getLoginByUserId(userId));
        if (loginResult.isEmpty()) {
            throw new Exception("Fail to get login, please contact to admin");
        }
        login = loginResult.get();
        login.setEmail(userMaster.getEmail());
        login.setMobile(userMaster.getMobile());
        login.setUpdatedBy(currentSession.getUser().getUserId());
        login.setUpdatedOn(currentDate);
        Login loginData = this.loginRepository.save(login);
        if (loginData == null) {
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
        userDetail.setUpdatedBy(currentSession.getUser().getUserId());
        userDetail.setUpdatedOn(currentDate);
        UserDetail userDetailData = userDetailRepository.save(userDetail);
        if (userDetail == null) {
            throw new Exception("fail to update UserDetail");
        }
        UserMedicalDetail userMedicalDetail;
        UserMedicalDetail userMedicalDetailResult = this.userMedicalDetailRepository.getUserMedicalDetailByUserId(userId);
        if (userMedicalDetailResult == null) {
            throw new Exception("UserMedicalDetail not found");
        }
        userMedicalDetailResult.setMedicalConsultancyId(userMaster.getMedicalConsultancyId());
        userMedicalDetailResult.setConsultedBy(userMaster.getConsultedBy());
        userMedicalDetailResult.setConsultedOn(utilDate);
        userMedicalDetailResult.setReferenceId(userMaster.getReferenceId());
        userMedicalDetailResult.setReportId(userMaster.getReportId());
        userMedicalDetailResult.setReportPath(userMaster.getReportPath());
        userMedicalDetailResult.setUpdatedBy(currentSession.getUser().getUserId());
        userMedicalDetailResult.setUpdatedOn(currentDate);
        UserMedicalDetail userMedicalDetailData = this.userMedicalDetailRepository.save(userMedicalDetailResult);
        return "User has been updated";
    }

    @Transactional(rollbackFor = Exception.class)
    public String updateUserServiceMobile(long userId,String userMaster, MultipartFile profileImage, MultipartFile[] userDocs) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        Optional<User> result = this.userRepository.findById(userId);
        if (result.isEmpty())
            throw new Exception("user record not found");

        User existingUser = objectMapper.readValue(userMaster, User.class);
        existingUser.setUpdatedBy(currentSession.getUser().getUserId());
        existingUser.setUpdatedOn(currentDate);
//        if (profileImage != null && !existingUser.getImageURL().isEmpty()) {
        if (profileImage != null) {
            var newFilePath = UpdateFile(profileImage, existingUser.getImageURL(), existingUser.getUserId(),"profile_","profile");
            existingUser.setImageURL(newFilePath);
        }

        userRepository.save(existingUser);

        UploadDocuments(userDocs, existingUser);

        // Delete Documents
        if (existingUser.getDeletedDocsId() != null){
            for (var docId : existingUser.getDeletedDocsId()) {
                var docPath = existingUser.getUserDocs().stream().filter(x-> x.getDocId()==docId).map(UserDocument::getDocPath).findFirst();
                if(docPath.isPresent())
                {
                    fileManager.DeleteFile(docPath.get());
                    userDocumentRepository.deleteById(docId);
                }
            }
        }

        Login login;
        Optional<Login> loginResult = Optional.ofNullable(this.loginRepository.getLoginByUserId(userId));
        if (loginResult.isEmpty()) {
            throw new Exception("Fail to get login, please contact to admin");
        }
        login = loginResult.get();
        login.setEmail(existingUser.getEmail());
        login.setMobile(existingUser.getMobile());
        login.setMobile(existingUser.getPassword());
        login.setUpdatedBy(currentSession.getUser().getUserId());
        login.setUpdatedOn(currentDate);
        this.loginRepository.save(login);

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
        try {
            var data = objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<UserMaster>>() {
            });

            if (data != null) {
                var result = data.get(0);

                result.setCategoryTypeIdList(Arrays.stream(objectMapper.readValue(result.getCategoryTypeIds(), Integer[].class)).toList());
                result.setJobLocationIdList(Arrays.stream(objectMapper.readValue(result.getJobLocationIds(), Integer[].class)).toList());

                return result;
            }

            return null;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> getUserByUserIdWithRelatedDataService(long userId) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_UserId", userId, Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_user_by_userid_with_related_data", dbParameters);
        var user = objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<UserMaster>>() {
        });
        var userDocs = objectMapper.convertValue(dataSet.get("#result-set-2"), new TypeReference<List<UserDocument>>() {
        });
        var educations = objectMapper.convertValue(dataSet.get("#result-set-3"), new TypeReference<List<Education>>() {
        });
        var religions = objectMapper.convertValue(dataSet.get("#result-set-4"), new TypeReference<List<Religion>>() {
        });
        var jobTypes = objectMapper.convertValue(dataSet.get("#result-set-5"), new TypeReference<List<JobType>>() {
        });
        var languages = objectMapper.convertValue(dataSet.get("#result-set-6"), new TypeReference<List<Language>>() {
        });
        var countries = objectMapper.convertValue(dataSet.get("#result-set-7"), new TypeReference<List<Country>>() {
        });
        Map<String, Object> result = new HashMap<>();
        result.put("User", user);
        result.put("UserDocs", userDocs);
        result.put("Educations", educations);
        result.put("Religions", religions);
        result.put("JobTypes", jobTypes);
        result.put("Languages", languages);
        result.put("Countries", countries);
        return result;
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
        if (loginResult == null) {
            throw new Exception("login record not found");
        }
        loginResult.setActive(false);
        this.loginRepository.save(loginResult);
        return "User data is De-active";
    }

    public ArrayList<UserInterests> updateUserInterestService(List<Integer> userInterest) throws Exception {
        ArrayList<UserInterests> userInterestList = new ArrayList<UserInterests>();
        for (var item : userInterest) {
            userInterestList.add(new UserInterests(currentSession.getUser().getUserId(), item));
        }
        userInterestsRepository.saveAll(userInterestList);
        return userInterestList;
    }

    public Map<String, Object> getJobandLocationService(int categoryId) {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_CategoryId", categoryId, Types.INTEGER));
        var dataSet = lowLevelExecution.executeProcedure("sp_jobtitle_by_filter", dbParameters);
        var country = objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<Country>>() {
        });
        var jobTitle = objectMapper.convertValue(dataSet.get("#result-set-2"), new TypeReference<List<JobType>>() {
        });
        Map<String, Object> response = new HashMap<>();
        response.put("Countries", country);
        response.put("JobTypes", jobTitle);
        return response;
    }

    public String addJobandLocationService(UserMaster user) throws Exception {
        var existingUserData = userRepository.findById(currentSession.getUser().getUserId());
        if (existingUserData.isEmpty())
            throw new Exception("User detail not found");

        var existingUser = existingUserData.get();

        // Add role Id
        existingUser.setRoleId(user.getRoleId());

        existingUser.setJobCategoryId(user.getJobCategoryId());
        if (user.getJobLocationIdList().size() > 0)
            existingUser.setJobLocationIds(objectMapper.writeValueAsString(user.getJobLocationIds()));

        if (user.getCategoryTypeIdList().size() > 0)
            existingUser.setCategoryTypeIds(objectMapper.writeValueAsString(user.getCategoryTypeIds()));

        var existingLoginDetail = loginRepository.getLoginByUserId(currentSession.getUser().getUserId());
        logger.info("User login data: " + objectMapper.writeValueAsString(existingLoginDetail));
        logger.info("User data: " + objectMapper.writeValueAsString(existingUser));

        existingLoginDetail.setAccountConfig(true);
        existingUser = userRepository.save(existingUser);
        existingLoginDetail = loginRepository.save(existingLoginDetail);

        logger.info("User login data: " + objectMapper.writeValueAsString(existingLoginDetail));
        logger.info("User data: " + objectMapper.writeValueAsString(existingUser));
        logger.info("Login and user detail saved successfully");
        return "Profile detail added successfully";
    }

    public List<User> getFriendsService() {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_UserId", currentSession.getUser().getUserId(), Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_get_friends_by_userid", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<User>>() {
        });
    }

    public List<User> filterFriendService(FilterModel filterModel) {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_searchString", filterModel.getSearchString(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_pageIndex", filterModel.getPageIndex(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_pageSize", filterModel.getPageSize(), Types.VARCHAR));
        var dataSet = lowLevelExecution.executeProcedure("sp_user_by_filter_name", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<User>>() {
        });
    }

    public String manageFriendService(long userId) throws Exception {
        if (userId == 0)
            throw new Exception("Invalid user selected");

        String status = "";
        var userData = userRepository.findById(currentSession.getUser().getUserId());
        if (userData.isEmpty())
            throw new Exception("User detail not found");

        var user = userData.get();
        List<Long> existingFriends = new ArrayList<>();
        if (!Objects.equals(user.getFriends(), "") && !Objects.equals(user.getFriends(), "[]")) {
            existingFriends = objectMapper.readValue(user.getFriends(), new TypeReference<List<Long>>() {
            });
        }
        if (existingFriends.contains(userId)) {
            existingFriends.remove(userId);
            status = "Unfriend successfully";
        } else {
            existingFriends.add(userId);
            status = "Friend added successfully";
        }

        user.setFriends(objectMapper.writeValueAsString(existingFriends));
        userRepository.save(user);
        return status;
    }

    @Override
    public String addUserImageService(MultipartFile userImage, Long userId) throws Exception {
        if (userId == 0)
            throw new Exception("Invalid user");

        var userData = userRepository.findById(userId);
        if (userData.isEmpty())
            throw new Exception("User not found");

        var user = userData.get();
        String filePath = saveUpdateFileDetail(user.getImageURL(), userImage, userId);
        user.setImageURL(filePath);
        userRepository.save(user);
        return filePath;
    }

    private String saveUpdateFileDetail(String oldFilePath, MultipartFile file, long userPostId) throws Exception {
        String filepath = null;
        if (file != null) {
            try {
                if (oldFilePath != null)
                    fileManager.DeleteFile(oldFilePath);

                filepath = fileManager.uploadFile(file, userPostId, "user_" + new Date().getTime(), "user");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return filepath;
    }
}