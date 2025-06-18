package com.zzcat.springaicatstudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * cors
 */
@Configuration
public class McvConfiguration implements WebMvcConfigurer {
    //添加cors
    //如果需要添加跨域配置，可以在这里实现 WebMvcConfigurer 接口的方法
    // 例如：addCorsMappings 方法来配置跨域请求的规则
     @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 允许所有来源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的HTTP方法
                .allowedHeaders("*")
                 .exposedHeaders("Content-Disposition");   // 允许所有请求头
    }
}
