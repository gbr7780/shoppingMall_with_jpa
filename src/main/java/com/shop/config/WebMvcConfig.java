package com.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // properties에 설정한 uploadPath값을 읽어온다
    @Value("${uploadPath}")
    String uploadPath;


    public void addResourceHandlers(ResourceHandlerRegistration registry) {
        // 브라우저에 url에 "/images로 시작하는 경우 uploadPath에 설정한 폴더 기준으로 파일을 읽어드린다.
        registry.addResourceLocations("/images/**")
                .addResourceLocations(uploadPath);
    }
}
