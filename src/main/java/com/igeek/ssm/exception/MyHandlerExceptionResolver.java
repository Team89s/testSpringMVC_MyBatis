package com.igeek.ssm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @Description 自定义的全局异常处理器
 * @Author chenmin
 * @Date 2021/3/10 15:08
 *
 * 全局异常处理器
 * 1.工作原理：service层抛出异常，controller层调用service继续抛出异常，抛给前端控制器DispatcherServlet处理，
 * 将异常统一交由全局异常处理器HandlerExceptionResolver处理。整个系统中只会有唯一的全局异常处理器。
 * 2.如何处理：自定义的全局异常处理器中，捕获异常信息，并将异常信息显示到指定的错误页面中展示。
 * 3.解决思路：
 * 若当前捕获到的异常是自定义的异常，则直接显示自定义的异常信息；
 * 若当前捕获到的异常不是自定义的异常，则显示为"未知错误"。
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o,
            Exception e) {

        CustomException exception = null;
        if(e instanceof CustomException){
            exception = (CustomException)e;
        }else{
            exception = new CustomException("未知错误");
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg",exception.getMessage());
        mv.setViewName("error.jsp");
        return mv;
    }
}
