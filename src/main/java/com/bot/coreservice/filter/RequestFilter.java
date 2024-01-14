package com.bot.coreservice.filter;

import com.bot.coreservice.entity.Login;
import com.bot.coreservice.model.EmployeeMaster;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bot.coreservice.entity.Employee;
import com.bot.coreservice.model.CurrentSession;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Component
public class RequestFilter implements WebFilter {
    @Autowired
    CurrentSession currentSession;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getPath().toString();
            if ("/hb/api/authenticate".equals(path)) {
                return chain.filter(exchange); // Skip the filter for other endpoints
            }
            HttpHeaders headers = request.getHeaders();
            Object headerUserDetail = headers.getFirst("userDetail");
            if(headerUserDetail == null || headerUserDetail.toString().isEmpty()) {
                throw new Exception("Invalid token found. Please contact to admin.");
            }

            var userData = objectMapper.readValue(headerUserDetail.toString(), Login.class);
            //currentSession.setEmployee(userData);
            //if (currentSession.getEmployee() == null)
              //  throw new Exception("Invalid token found. Please contact to admin.");

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unauthorized access. Please try with valid token.");
        }
        return chain.filter(exchange);
    }
}
