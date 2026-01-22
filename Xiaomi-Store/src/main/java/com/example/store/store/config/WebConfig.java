package com.example.store.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor auth;

    public WebConfig(AuthInterceptor auth) {
        this.auth = auth;
    }

    @Override
    public void addInterceptors(InterceptorRegistry reg) {
        reg.addInterceptor(auth)
           .addPathPatterns("/cart/**", "/order/**", "/account/**")
           .excludePathPatterns("/login", "/register", "/activate", "/forgot-password", "/reset-password", "/product/**");
    }
}
