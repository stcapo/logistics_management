package com.logistics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
               .addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/css/**")
               .addResourceLocations("classpath:/static/css/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")           // 拦截所有请求
                .excludePathPatterns(
                        "/welcome",               // 不拦截欢迎页面
                        "/user/login",            // 不拦截登录页面
                        "/user/register",         // 不拦截注册接口
                        "/user/resetPassword",    // 不拦截重置密码接口
                        "/css/**",                // 不拦截CSS静态资源
                        "/js/**",                 // 不拦截JavaScript静态资源
                        "/img/**",                // 不拦截图片资源
                        "/fonts/**"               // 不拦截字体资源
                );
    }
}
