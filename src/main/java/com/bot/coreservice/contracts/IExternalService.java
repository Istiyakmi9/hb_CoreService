package com.bot.coreservice.contracts;

import com.bot.coreservice.model.ResumeModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface IExternalService {
    byte[] CallPostRequest(ResumeModel resumeModel) throws Exception;
}
