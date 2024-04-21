package com.bot.coreservice.controller;

import com.bot.coreservice.contracts.IResumeGenerator;
import com.bot.coreservice.model.ApiResponse;
import com.bot.coreservice.model.FilterModel;
import com.bot.coreservice.model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hb/api/core/resume/")
public class ResumeController {

    @Autowired
    IResumeGenerator resumeGenerator;

    @PostMapping("generateResume")
    public ResponseEntity<ApiResponse> generateResume(@RequestBody RequestModel requestModel) throws Exception {
        var result = resumeGenerator.ResumeGeneratorService(requestModel);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }
}
