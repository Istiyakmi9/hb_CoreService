package com.bot.coreservice.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadRequestFormData {
    private String userPost;

    private MultipartFile postImages;

    private MultipartFile postVideos;
}
