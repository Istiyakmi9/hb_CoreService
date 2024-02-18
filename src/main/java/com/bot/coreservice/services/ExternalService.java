package com.bot.coreservice.services;

import com.bot.coreservice.contracts.IExternalService;
import com.bot.coreservice.model.ResumeModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService implements IExternalService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(ExternalService.class);

    public byte[] CallPostRequest(ResumeModel resumeModel) throws Exception {
        ResponseEntity<byte[]> response = null;

        try {
            String jsonBody = objectMapper.writeValueAsString(resumeModel);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
            response = restTemplate.postForEntity("https://www.emstum.com/bot/dn/gen/api/FileGenerator/generate_pdf_bytes", requestEntity, byte[].class);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new Exception(ex.getMessage());
        }

        return response.getBody();
    }
}
