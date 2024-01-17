package com.bot.coreservice.filter;

import com.bot.coreservice.config.RouteValidator;
import com.bot.coreservice.entity.Login;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bot.coreservice.model.CurrentSession;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.util.context.Context;
import reactor.util.context.ContextView;

import java.util.Objects;

//@Component
//@Order(1)
public class RequestFilter implements WebFilter {
    @Autowired
    CurrentSession currentSession;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private RouteValidator routeValidator;
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);

    @NotNull
    @Override
    public Mono<Void> filter(@NotNull ServerWebExchange exchange, @NotNull WebFilterChain chain) {
        try {
            LOGGER.info("[CORE SERVICE REQUEST]: " + exchange.getRequest().getURI().getPath());

            if (exchange.getRequest().getURI().getPath().startsWith("/resources/")) {
                return chain.filter(exchange);
            }

            if (routeValidator.isSecured.test(exchange.getRequest())) {
                ServerHttpRequest request = exchange.getRequest();
                HttpHeaders headers = request.getHeaders();
                Object headerUserDetail = headers.getFirst("userDetail");
                if (headerUserDetail == null || headerUserDetail.toString().isEmpty()) {
                    throw new Exception("Invalid token found. Please contact to admin.");
                }

                var userData = objectMapper.readValue(headerUserDetail.toString(), CurrentSession.class);
                if (userData == null)
                  throw new Exception("Invalid token found. Please contact to admin.");

                var loginDetail = objectMapper.readValue(userData.getUserDetail(), Login.class);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unauthorized access. Please try with valid token.");
        }
        return chain.filter(exchange).doFinally(signalType -> {
            // This callback is executed after the controller completes its work
            if (signalType.equals(SignalType.ON_COMPLETE)) {
                // Your callback logic here
                System.out.println("Callback executed after controller completion");
            }
        });
    }
}
