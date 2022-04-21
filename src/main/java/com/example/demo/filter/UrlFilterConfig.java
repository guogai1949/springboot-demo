package com.example.demo.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class UrlFilterConfig implements WebMvcConfigurer {

    @Resource
    private UrlInterceptor urlInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(urlInterceptor)
                .addPathPatterns("/test")
                .excludePathPatterns("/index");
    }
}
