package com.tutorial.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jimmy. 2018/3/30  16:10
 * session 拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * controller 执行之前调用
     * @param request
     * @param response
     * @param object
     * @return
     * @throws IOException
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws IOException {
        if(request.getRequestURI().equals("/login")||request.getRequestURI().equals("/index")){
            return true;
        }
        //验证session是否存在
        Object obj = request.getSession().getAttribute("session_user");
        if(obj == null){
            response.sendRedirect("/index");
            return false;
        }
        return true;
    }


    /**
     * controller 执行之后,且页面渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("------postHandle-----");
    }

    /**
     * 页面渲染之后调用,一般用于资源清理操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("------afterCompletion-----");

    }
}
