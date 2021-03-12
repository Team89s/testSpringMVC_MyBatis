package com.igeek.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0
 * @Description TODO
 * @Author chenmin
 * @Date 2021/3/12 10:55
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public @ResponseBody String hello(){
        return "hello world!!!";
    }
}
