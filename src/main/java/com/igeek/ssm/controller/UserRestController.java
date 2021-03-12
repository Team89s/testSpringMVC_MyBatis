package com.igeek.ssm.controller;

import com.igeek.ssm.pojo.User;
import com.igeek.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @version 1.0
 * @Description TODO
 * @Author chenmin
 * @Date 2021/3/11 16:07
 */
@Controller
public class UserRestController {

    @Autowired
    private UserService userService;

    //查询用户列表
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String findUserList(Model model){
        List<User> users = userService.selectUserList();
        model.addAttribute("users",users);
        return "/user/userList.jsp";
    }

    /**
     * 查询单个用户 http://localhost:8899/user/10  等价于将key=id,value=10的请求参数传递至controller
     * 地址栏中的数据10,就是通过@RequestMapping中{key}接收数据的
     * @PathVariable("key") 必须与@RequestMapping中{key}一致；若@RequestMapping中{key}是与形参名称一样，可直接使用@PathVariable
     *
     * @PathVariable 此注解解析路径中的变量
     */
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String findUserById(@PathVariable Integer id,Model model){
        User user = userService.selectUserOne(id);
        model.addAttribute("user",user);
        return "/user/userEdit.jsp";
    }

    //用户注册
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String insertUser(User user){
        userService.insertUser(user);
        return "redirect:users";
    }

    //删除用户  http://localhost:8899/delete/28  --> http://localhost:8899/users
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return "redirect:../users";
    }

    //修改用户
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:users";
    }


    //登录
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(User user , HttpSession session){
        user = userService.login(user.getUsername(), user.getAddress());
        if (user!=null) {
            //登录成功
            session.setAttribute("user",user);
            return "success.jsp";
        }
        return "login.jsp";
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login.jsp";
    }
}
