package com.learn.clinic.config;

import com.learn.clinic.interceptor.PageHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Mvc 配置类
 *
 * @author Milk
 * @version 2023/12/28 10:46
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final PageHandlerInterceptor pageHandlerInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pageHandlerInterceptor);
    }
}
