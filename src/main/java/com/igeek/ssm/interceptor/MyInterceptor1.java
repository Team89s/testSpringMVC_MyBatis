package com.igeek.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @Description 拦截器
 * @Author chenmin
 * @Date 2021/3/12 10:29
 */
public class MyInterceptor1 implements HandlerInterceptor {

    /**
     * preHandle  在进入执行的handler之前
     * 使用场景：登录验证、权限校验等
     * @param request  请求
     * @param response 响应
     * @param handler  将要执行的handler
     * @return 若返回true，则代表放行；若返回false，则代表不放行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor1 preHandle...");
        return true;
    }

    /**
     * postHandle  在执行handler之后，返回ModelAndView对象之前
     * 使用场景：返回最终数据及视图之前，需要处理的公共数据（公共的菜单栏等）
     * @param request   请求
     * @param response  响应
     * @param handler   执行的handler
     * @param modelAndView 操作的数据及逻辑视图
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor1 postHandle...");
    }

    /**
     * afterCompletion  执行完毕handler方法之后
     * 使用场景：统一日志处理、统一异常的处理等
     * @param request  请求
     * @param response 响应
     * @param handler  执行的handler
     * @param ex       异常信息
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor1 afterCompletion...");
    }
}
