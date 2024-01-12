package com.bot.coreservice.services;

import com.bot.coreservice.model.FileDetail;
import com.bot.coreservice.model.FileStorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileManager {
    private final Logger logger = LoggerFactory.getLogger(FileManager.class);
    private final String basePath;

    @Autowired
    public FileManager(FileStorageProperties fileStorageProperties) throws IOException {
        logger.info("Getting static folder class path");
        // basePath = getClass().getClassLoader().getResource("static").getPath();
        basePath = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize().toString();
        logger.info("Static folder class path: " + basePath);
    }

    public String uploadFile(FilePart file, long userId, String fileName) throws Exception {
        FileDetail fileDetail = null;
        String name = file.filename();
        if (file != null && name != null && !name.isEmpty()) {
            fileDetail = new FileDetail();
            String ext = name.substring(name.lastIndexOf(".") + 1);
            String nameOnly = name.substring(0, name.lastIndexOf("."));
            String relativePath = Paths.get("post_" + String.valueOf(userId)).toString();

            if(name.contains(".."))
                throw new Exception("File name contain invalid character.");

            Path targetDirectory = Paths.get(basePath, relativePath)
                    .toAbsolutePath()
                    .normalize();
            if(Files.notExists(targetDirectory))
                Files.createDirectories(targetDirectory);

            String newFileName = null;
            if (fileName.isEmpty()) {
                newFileName = nameOnly;
            } else {
                newFileName = fileName + "." + ext;
            }

            Path targetPath = targetDirectory.resolve(newFileName);
            fileDetail.setFilePath(relativePath);
            file.transferTo(targetPath).block();
            return (Paths.get(relativePath, newFileName).toString());
        } else {
            return name;
        }
    }
}
