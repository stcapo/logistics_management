package com.logistics.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取Session
        HttpSession session = request.getSession();
        
        // 检查用户是否已登录
        if (session.getAttribute("currentUser") != null) {
            // 已登录，允许访问
            return true;
        }
        
        // 未登录，重定向到欢迎页面
        response.sendRedirect(request.getContextPath() + "/welcome");
        return false;
    }
}
