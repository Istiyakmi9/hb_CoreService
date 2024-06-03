package com.bot.coreservice.services;

import com.bot.coreservice.Repository.*;
import com.bot.coreservice.contracts.IUserPostsService;
import com.bot.coreservice.db.LowLevelExecution;
import com.bot.coreservice.entity.*;
import com.bot.coreservice.model.Currency;
import com.bot.coreservice.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
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
public class UserPostsServiceImpl implements IUserPostsService {

    @Autowired
    UserPostsRepository userPostsRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    FileManager fileManager;
    @Autowired
    JobRequirementRepository jobRequirementRepository;
    @Autowired
    LowLevelExecution lowLevelExecution;
    @Autowired
    JobTypeRepository jobTypeRepository;
    @Autowired
    LikedPostsRepository likedPostsRepository;
    @Autowired
    AppliedPostsRepository appliedPostsRepository;
    @Autowired
    CurrentSession currentSession;

    private final Logger logger = LoggerFactory.getLogger(ExternalService.class);

    public String addUserPostService(UserPosts userPost) {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        var lastUserPostRecord = this.userPostsRepository.getLastUserPostRecord();
        if (lastUserPostRecord == null) {
            userPost.setUserPostId(1L);
        } else {
            userPost.setUserPostId(lastUserPostRecord.getUserPostId() + 1);
        }
        userPost.setPostedOn(currentDate);
        this.userPostsRepository.save(userPost);

        return "Posted successfully";
    }

    public String updateUserPostService(UserPosts userPost, long userPostId) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        Optional<UserPosts> result = this.userPostsRepository.findById(userPostId);
        if (result.isEmpty()) {
            throw new Exception("No user post found");
        }
        UserPosts existingUserPost = result.get();
        existingUserPost.setShortDescription(userPost.getShortDescription());
        existingUserPost.setCompleteDescription(userPost.getCompleteDescription());
        existingUserPost.setCategoryTypeId(1);
        existingUserPost.setJobRequirementId(1);
        existingUserPost.setUpdatedOn(currentDate);
        this.userPostsRepository.save(existingUserPost);
        return "User post has been updated";
    }

    public List<UserPosts> getHomePageService(int page, int pageSize) {
        return getPosts(page, pageSize, Constants.USERPOSTS_FILTER);
    }

    public List<UserPosts> getOwnPageService(int page, int pageSize) {
        return getPosts(page, pageSize, Constants.OWN_POSTS);
    }

    public List<UserPosts> getPosts(int page, int pageSize, String procedure) {
        if (page == 0)
            page = 1;

        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_userId", currentSession.getUser().getUserId(), Types.BIGINT));
        dbParameters.add(new DbParameters("_pageIndex", page, Types.INTEGER));
        dbParameters.add(new DbParameters("_pageSize", pageSize, Types.INTEGER));
        var dataSet = lowLevelExecution.executeProcedure(procedure, dbParameters);
        var result = objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<UserPosts>>() {
        });

        if (result != null && !result.isEmpty()) {
            result.forEach(x -> {
                if (!Objects.equals(x.getFileDetail(), "[]")) {
                    try {
                        x.setFiles(objectMapper.readValue(x.getFileDetail(), new TypeReference<List<FileDetail>>() {
                        }));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        return result;
    }

    public Map<String, Object> getUserPostByUserPostIdService(long userPostId) throws Exception {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_UserPostId", userPostId, Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_userposts_getbyid", dbParameters);
        var userPost = objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<UserPostRequest>>() {
        });
        var countries = objectMapper.convertValue(dataSet.get("#result-set-2"), new TypeReference<List<Country>>() {
        });
        var currencies = objectMapper.convertValue(dataSet.get("#result-set-3"), new TypeReference<List<Currency>>() {
        });
        var jobTypes = objectMapper.convertValue(dataSet.get("#result-set-4"), new TypeReference<List<JobType>>() {
        });
        Map<String, Object> result = new HashMap<>();
        result.put("UserPost", userPost);
        result.put("Countries", countries);
        result.put("Currencies", currencies);
        result.put("JobTypes", jobTypes);
        return result;
    }

    public List<UserPosts> deleteUserPostByUserPostIdService(long userPostId) throws Exception {
        this.userPostsRepository.deleteById(userPostId);
        return getPostByUserIdService(currentSession.getUser().getUserId());
    }

    public List<UserPosts> uploadUserPostsService(String userPost, MultipartFile[] postImages) throws Exception {
        // Save user post
        saveUserPostedData(userPost, postImages);
        // Get latest data
        return getHomePageService(1, 20);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveUserPostedData(String userPost, MultipartFile[] postImages) throws Exception {
        try {
            UserPostRequest userPostRequest = objectMapper.readValue(userPost, UserPostRequest.class);
            UserPosts userPosts = objectMapper.convertValue(userPostRequest, UserPosts.class);
            JobRequirement jobRequirement = objectMapper.convertValue(userPostRequest, JobRequirement.class);

            jobRequirement.setRequiredShortDesc(userPostRequest.getShortDescription());
//            jobRequirement.setJobTypeId(userPosts.getCategoryTypeId());

            var jobRequirementId = addJobRequirement(jobRequirement);
            var lastUserPostRecord = this.userPostsRepository.getLastUserPostRecord();
            if (lastUserPostRecord == null)
                userPosts.setUserPostId(1L);
            else
                userPosts.setUserPostId(lastUserPostRecord.getUserPostId() + 1);

            userPosts.setJobRequirementId(jobRequirementId);
            userPostRequest.setUserPostId(userPosts.getUserPostId());

            var fileDetail = saveUpdateFileDetail(userPostRequest.getFileDetail(), postImages, userPosts.getUserPostId());
            if (fileDetail != null) {
                var jsonFileDetail = objectMapper.writeValueAsString(fileDetail);
                userPosts.setFileDetail(jsonFileDetail);
            } else {
                userPosts.setFileDetail("[]");
            }

            addUserPostDetailService(userPosts);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    private void addUserPostDetailService(UserPosts userPosts) {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        userPosts.setPostedBy(currentSession.getUser().getUserId());
        userPosts.setPostedOn(currentDate);
        userPosts.setUpdatedOn(currentDate);
        if (currentSession.getUser().getLastName() == null)
            userPosts.setFullName(currentSession.getUser().getFirstName());
        else
            userPosts.setFullName(currentSession.getUser().getFirstName() + " " + currentSession.getUser().getLastName());

        this.userPostsRepository.save(userPosts);
    }

    private long addJobRequirement(JobRequirement jobRequirement) {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        var lastJobRequirementRecord = this.jobRequirementRepository.getLastJobRequirementRecord();
        if (lastJobRequirementRecord == null)
            jobRequirement.setJobRequirementId(1L);
        else
            jobRequirement.setJobRequirementId(lastJobRequirementRecord.getJobRequirementId() + 1);

        jobRequirement.setCreatedBy(currentSession.getUser().getUserId());
        jobRequirement.setCreatedOn(currentDate);
        this.jobRequirementRepository.save(jobRequirement);
        return jobRequirement.getJobRequirementId();
    }

    public List<UserPosts> updateUserPostsService(String userPost, MultipartFile[] postImages) throws Exception {
        saveUpdatedUserPosts(userPost, postImages);

        return getHomePageService(1, 20);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveUpdatedUserPosts(String userPost, MultipartFile[] postImages) throws Exception {
        UserPostRequest userPostRequest = objectMapper.readValue(userPost, UserPostRequest.class);
        if (userPostRequest.getUserPostId() == 0)
            throw new Exception("Invalid post selected");

        if (userPostRequest.getJobRequirementId() == 0)
            throw new Exception("Invalid Job requirement id");

        updateJobRequirementService(userPostRequest);
        updateUserPostService(userPostRequest, postImages);
    }

    private void updateUserPostService(UserPostRequest userPostRequest, MultipartFile[] postImages) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        var data = this.userPostsRepository.findById(userPostRequest.getUserPostId());
        if (data.isEmpty())
            throw new Exception("Post data not found");

        UserPosts existingUserPost = data.get();
        existingUserPost.setShortDescription(userPostRequest.getShortDescription());
        existingUserPost.setCompleteDescription(userPostRequest.getCompleteDescription());
        existingUserPost.setCategoryTypeId(userPostRequest.getCategoryTypeId());
        existingUserPost.setCountryId((userPostRequest.getCountryId()));
        existingUserPost.setJobCategoryId(userPostRequest.getJobCategoryId());
        var fileDetail = saveUpdateFileDetail(existingUserPost.getFileDetail(), postImages, userPostRequest.getUserPostId());

        if (fileDetail != null && !fileDetail.isEmpty()) {
            var jsonFileDetail = objectMapper.writeValueAsString(fileDetail);
            existingUserPost.setFileDetail(jsonFileDetail);
        }
        existingUserPost.setUpdatedOn(currentDate);
        this.userPostsRepository.save(existingUserPost);
    }

    private void updateJobRequirementService(UserPostRequest userPostRequest) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        var result = this.jobRequirementRepository.findById(userPostRequest.getJobRequirementId());
        if (result.isEmpty())
            throw new Exception("JobRequirement record not found");

        JobRequirement existingjobRequirement = result.get();
        existingjobRequirement.setRequiredShortDesc(userPostRequest.getShortDescription());
        existingjobRequirement.setCompleteDescription(userPostRequest.getCompleteDescription());
//        existingjobRequirement.setJobTypeId(userPostRequest.getCategoryTypeId());
        existingjobRequirement.setIsHRAAllowance(userPostRequest.getIsHRAAllowance());
        existingjobRequirement.setHRAAllowanceAmount(userPostRequest.getHRAAllowanceAmount());
        existingjobRequirement.setIsTravelAllowance(userPostRequest.getIsTravelAllowance());
        existingjobRequirement.setTravelAllowanceAmount(userPostRequest.getTravelAllowanceAmount());
        existingjobRequirement.setIsFoodAllowance(userPostRequest.getIsFoodAllowance());
        existingjobRequirement.setFoodAllowanceAmount(userPostRequest.getFoodAllowanceAmount());
        existingjobRequirement.setIsForeignReturnCompulsory(userPostRequest.getIsForeignReturnCompulsory());
        existingjobRequirement.setMinimumDaysRequired(userPostRequest.getMinimumDaysRequired());
        existingjobRequirement.setMinimumCTC(userPostRequest.getMinimumCTC());
        existingjobRequirement.setMaximumCTC(userPostRequest.getMaximumCTC());
        existingjobRequirement.setIsOTIncluded(userPostRequest.getIsOTIncluded());
        existingjobRequirement.setMaxOTHours(userPostRequest.getMaxOTHours());
        existingjobRequirement.setBonus(userPostRequest.getBonus());
        existingjobRequirement.setCountryId(userPostRequest.getCountryId());
        existingjobRequirement.setMinAgeLimit(userPostRequest.getMinAgeLimit());
        existingjobRequirement.setMaxAgeLimit(userPostRequest.getMaxAgeLimit());
        existingjobRequirement.setNoOfPosts(userPostRequest.getNoOfPosts());
        existingjobRequirement.setSalaryCurrency(userPostRequest.getSalaryCurrency());
        existingjobRequirement.setContractPeriodInMonths(userPostRequest.getContractPeriodInMonths());
        existingjobRequirement.setUpdatedBy(currentSession.getUser().getUserId());
        existingjobRequirement.setUpdatedOn(currentDate);
    }

    private List<FileDetail> saveUpdateFileDetail(String fileDetailJSON, MultipartFile[] files, long userPostId) throws Exception {
        if (files != null) {
            List<FileDetail> existingFiles;
            int id = 0;
            if (fileDetailJSON != null && !fileDetailJSON.equals("[]")) {
                existingFiles = objectMapper.readValue(fileDetailJSON, new TypeReference<List<FileDetail>>() {
                });

                existingFiles.sort(Comparator.comparingInt(FileDetail::getFileDetailId).reversed());
                id = existingFiles.get(0).getFileDetailId();
            } else {
                existingFiles = new ArrayList<>();
                id = 1;
            }

            for (var x : files) {
                FileDetail fileDetail = new FileDetail();
                String filepath = null;
                try {
                    filepath = fileManager.uploadFile(x, userPostId, "post_" + new Date().getTime(), "post");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                fileDetail.setFileDetailId(id++);
                fileDetail.setFilePath(filepath);
                existingFiles.add(fileDetail);
            }
            return existingFiles;
        }

        return null;
    }

    @Override
    public List<FileDetail> deleteImagesService(Long userPostId, int fileDetailId) throws Exception {
        try {
            var existingUserPostData = this.userPostsRepository.findById(userPostId);
            if (existingUserPostData.isPresent()) {
                var existingUserPost = existingUserPostData.get();

                if (existingUserPost.getFileDetail() == null || existingUserPost.getFileDetail().equals("[]")) {
                    throw new Exception("File not found");
                }

                var existingFiles = objectMapper.readValue(existingUserPost.getFileDetail(), new TypeReference<List<FileDetail>>() {
                });
                var file = existingFiles.stream().filter(x -> x.getFileDetailId() == fileDetailId).findFirst().orElse(null);
                if (file == null)
                    throw new Exception("File detail not found");

                var updatedFiles = existingFiles.stream().filter(x -> x.getFileDetailId() != fileDetailId).toList();
                existingUserPost.setFileDetail(objectMapper.writeValueAsString(updatedFiles));
                fileManager.DeleteFile(file.getFilePath());
                userPostsRepository.save(existingUserPost);
                return updatedFiles;
            } else {
                throw new Exception("UserPostId does not exists");
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<JobType> getAllJobTypeService() {
        return jobTypeRepository.findAll();
    }

    public UserPostRequest getPostByPostIdService(Long postId) throws Exception {
        if (postId == 0)
            throw new Exception("Invalid post Id");

        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_userPostId", postId, Types.BIGINT));
        dbParameters.add(new DbParameters("_userId", currentSession.getUser().getUserId(), Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_userposts_getby_postid", dbParameters);
        var userPostRequest = objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<UserPostRequest>>() {
        });

        if (userPostRequest != null && !userPostRequest.isEmpty()) {
            userPostRequest.forEach(x -> {
                if (!Objects.equals(x.getFileDetail(), "[]")) {
                    try {
                        x.setFiles(objectMapper.readValue(x.getFileDetail(), new TypeReference<List<FileDetail>>() {
                        }));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        assert userPostRequest != null;
        return userPostRequest.get(0);
    }

    public List<UserPosts> getPostByUserIdService(Long userId) throws Exception {
        if (userId == 0)
            throw new Exception("Invalid userId");

        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_UserId", userId, Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_userposts_getby_userid", dbParameters);
        var result = objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<UserPosts>>() {
        });
        if (result != null && !result.isEmpty()) {
            result.forEach(x -> {
                if (!Objects.equals(x.getFileDetail(), "[]")) {
                    try {
                        x.setFiles(objectMapper.readValue(x.getFileDetail(), new TypeReference<List<FileDetail>>() {
                        }));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        return result;
    }

    public String addLikedPostService(UserPosts userPost) throws Exception {
        var existingPost = likedPostsRepository.existingLikedPostBy(userPost.getUserPostId(), currentSession.getUser().getUserId());
        if (existingPost == null)
            addLikedPost(userPost, currentSession.getUser().getUserId());

        return "Thanks for Like";
    }

    private void addLikedPost(UserPosts userPost, long userId) {
        var lastLikePost = likedPostsRepository.getLastLikedPost();
        LikedPosts likedPosts = getLikedPosts(userPost, userId, lastLikePost);
        this.likedPostsRepository.save(likedPosts);
    }

    @NotNull
    private static LikedPosts getLikedPosts(UserPosts userPost, long userId, LikedPosts lastLikePost) {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        LikedPosts likedPosts = new LikedPosts();
        if (lastLikePost == null)
            likedPosts.setLikedPostsId(1L);
        else
            likedPosts.setLikedPostsId(lastLikePost.getLikedPostsId() + 1);

        likedPosts.setPostId(userPost.getUserPostId());
        likedPosts.setUserId(userId);
        likedPosts.setPostUserId(userPost.getPostedBy());
        likedPosts.setLiked(true);
        likedPosts.setLikedOn(currentDate);
        likedPosts.setLongitude("");
        likedPosts.setLatitude("");
        return likedPosts;
    }

    public String addAppliedPostService(UserPosts userPost) throws Exception {
        var existingPost = appliedPostsRepository.existingAppliedPostBy(userPost.getUserPostId(), currentSession.getUser().getUserId());
        if (existingPost == null)
            return addAppliedPost(userPost.getUserPostId());

        return "fail";
    }

    public String addAppliedPost(Long postIs) throws Exception {
        if (postIs == 0)
            throw new Exception("Invalid post detail");

        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_postId", postIs, Types.BIGINT));
        dbParameters.add(new DbParameters("_userId", currentSession.getUser().getUserId(), Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_liked_posts_ins", dbParameters, true);

        if (dataSet.get("_ProcessingResult") != null && Objects.equals(dataSet.get("_ProcessingResult").toString(), "inserted"))
            return "success";

        return "fail";
    }

}