package com.bot.coreservice.contracts;

import com.bot.coreservice.model.RequestModel;

import java.io.IOException;

public interface IResumeGenerator {
    String ResumeGeneratorService(RequestModel requestModel) throws IOException;
}
