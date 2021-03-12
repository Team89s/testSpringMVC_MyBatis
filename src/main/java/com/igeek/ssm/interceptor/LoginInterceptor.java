package com.igeek.ssm.interceptor;

import com.igeek.ssm.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @version 1.0
 * @Description 登录拦截器
 * @Author chenmin
 * @Date 2021/3/12 10:29
 */
public class LoginInterceptor implements HandlerInterceptor {

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
        System.out.println("LoginInterceptor preHandle...");

        //登录页面或者登出操作  放行
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = "+requestURI); //访问的地址 /hello
        if(requestURI.contains("login") || requestURI.contains("logout")){
            return true;
        }

        //登录，放行
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user!=null){
            //放行
            return true;
        }

        request.getRequestDispatcher("login.jsp").forward(request,response);
        //不放行
        return false;
    }

}
