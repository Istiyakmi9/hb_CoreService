package com.bot.coreservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bot.coreservice.entity.Employee;
import com.bot.coreservice.model.CurrentSession;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Component
public class RequestFilter implements Filter {
    @Autowired
    CurrentSession currentSession;

    @Autowired
    ObjectMapper objectMapper;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            Object headerUserDetail = ((HttpServletRequest) servletRequest).getHeader("userDetail");
            if(headerUserDetail == null || headerUserDetail.toString().isEmpty()) {
                throw new Exception("Invalid token found. Please contact to admin.");
            }

            Object database = ((HttpServletRequest) servletRequest).getHeader("database");
            if(database == null || database.toString().isEmpty()) {
                throw new Exception("Invalid company code found. Please contact to admin.");
            }

            var userData = objectMapper.readValue(headerUserDetail.toString(), Employee.class);
            currentSession.setEmployee(userData);
            if (currentSession.getEmployee() == null)
                throw new Exception("Invalid token found. Please contact to admin.");

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unauthorized access. Please try with valid token.");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
