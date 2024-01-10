package com.bot.coreservice.authenticationmodule;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .exposedHeaders("*")
                .allowedMethods("GET", "PUT", "POST", "DELETE", "HEAD", "OPTIONS")
                .allowCredentials(false);
    }
}
