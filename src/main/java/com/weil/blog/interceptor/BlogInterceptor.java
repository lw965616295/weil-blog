package com.weil.blog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName BlogInterceptor
 * @Author weil
 * @Description //拦截器定义
 * @Date 2022/6/29 10:24
 * @Version 1.0.0
 **/
@Component
@Slf4j
public class BlogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("进入拦截器,{}", request.getRequestURI());
        // 对于未登录的用户，重定向去登录
        if (request.getSession().getAttribute("user") == null){
            request.getSession().setAttribute("msg", "请重新登录！");
            response.sendRedirect("/admin/login");
            return false;
        }
        request.getSession().removeAttribute("msg");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
