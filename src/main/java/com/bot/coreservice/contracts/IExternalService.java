package com.bot.coreservice.contracts;

import com.bot.coreservice.entity.ResumeDetail;
import com.bot.coreservice.model.ResumeModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface IExternalService {
    byte[] CallPostRequest(ResumeModel resumeModel) throws Exception;


    ResumeDetail getResumeDetailService(long resumeId);
}
