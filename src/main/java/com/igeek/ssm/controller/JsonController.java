package com.igeek.ssm.controller;

import com.igeek.ssm.pojo.Items;
import com.igeek.ssm.pojo.User;
import com.igeek.ssm.service.ItemsService;
import com.igeek.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @version 1.0
 * @Description 解析json数据
 * @Author chenmin
 * @Date 2021/3/11 9:39
 */
@Controller
public class JsonController {

    @Autowired
    private ItemsService itemsService;
    @Autowired
    private UserService userService;

    /**
     * 请求json数据，响应json数据
     * @RequestBody   将请求中的json数据，转化成Java对象
     * @ResponseBody  将响应的Java对象，转化成json数据
     */
    @RequestMapping("/testJson.action")
    public @ResponseBody Items testJson(@RequestBody Items items){
        //以Java对象继续操作
        items.setPrice(9.9F);
        return items;
    }

    /**
     * 请求key/value数据，响应json数据
     * @ResponseBody  将响应的Java对象，转化成json数据
     */
    @RequestMapping("/testKey.action")
    public @ResponseBody Items testkey(Items items){
        //以Java对象继续操作
        items.setPrice(9.9F);
        return items;
    }


    //响应json串，json数据是集合类型
    @RequestMapping("/testList.action")
    @ResponseBody
    public List<Items> testList(){
        List<Items> list = itemsService.selectList();
        return list;
    }


    @RequestMapping("/testJsonList.action")
    public @ResponseBody List<Items> testJsonList(@RequestBody List<Items> itemsList){
        return itemsList;
    }

    //通过姓名和地址查询用户信息
    @RequestMapping("/userLogin.action")
    @ResponseBody
    public User userLogin(String username,String address){
        User user = userService.login(username, address);
        return user;
    }
}
