package com.bot.coreservice.controller;

import com.bot.coreservice.contracts.IExternalService;
import com.bot.coreservice.model.ApiResponse;
import com.bot.coreservice.model.ResumeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hb/api/core/resume/")
public class ResumeController {
    @Autowired
    IExternalService externalService;

    @PostMapping("convert_to_pdf")
    public ResponseEntity<ApiResponse> getPdfFile(@RequestBody ResumeModel resumeModel) throws Exception {
        byte[] byteResponse = externalService.CallPostRequest(resumeModel);
        return ResponseEntity.ok(ApiResponse.Ok(byteResponse));
    }

}
