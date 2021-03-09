package com.igeek.ssm.controller;

import com.igeek.ssm.pojo.Items;
import com.igeek.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    //查询商品列表
    @RequestMapping("/queryItemsList.action")
    public ModelAndView queryItemsList(){
        List<Items> itemsList = itemsService.selectList();
        ModelAndView mv = new ModelAndView();
        //将数据添加到请求中
        mv.addObject("itemsList",itemsList);
        //添加逻辑视图名
        mv.setViewName("items/itemsList");
        return mv;
    }

}
