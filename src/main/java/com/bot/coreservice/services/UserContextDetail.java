package com.bot.coreservice.services;

import com.bot.coreservice.entity.Login;
import com.bot.coreservice.filter.RequestFilter;
import com.bot.coreservice.model.CurrentSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class UserContextDetail {
    @Autowired
    ObjectMapper objectMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

    public Login getCurrentUserDetail(ServerWebExchange exchange) throws Exception {
        Login loginDetail = null;
        try {
            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders headers = request.getHeaders();
            Object headerUserDetail = headers.getFirst("userDetail");
            if (headerUserDetail == null || headerUserDetail.toString().isEmpty()) {
                throw new Exception("Invalid token found. Please contact to admin.");
            }

            var userData = objectMapper.readValue(headerUserDetail.toString(), CurrentSession.class);
            if (userData == null)
                throw new Exception("Invalid token found. Please contact to admin.");

            loginDetail = objectMapper.readValue(userData.getUserDetail(), Login.class);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new Exception("Current userdetail not found from the request session");
        }

        return loginDetail;
    }
}
