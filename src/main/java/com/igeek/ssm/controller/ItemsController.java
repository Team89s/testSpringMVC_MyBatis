package com.igeek.ssm.controller;

import com.igeek.ssm.exception.CustomException;
import com.igeek.ssm.pojo.Items;
import com.igeek.ssm.pojo.ItemsCustom;
import com.igeek.ssm.service.ItemsService;
import com.igeek.ssm.validate.ValidateGroup1;
import com.igeek.ssm.vo.ItemsQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
//窄化请求映射
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    /**
     * 使用@ModelAttribute("key")注解，在请求域中存储方法的返回值，在页面中${key}使用
     *
     * 相当于：
     * request.setAttribute("key",value);
     * request.getAttribute("key"); --->  ${key}
     * @return
     */
    @ModelAttribute("getMap")
    public Map<String,String> get(){
        Map<String,String> map = new HashMap<>();
        map.put("1","鼠标");
        map.put("2","键盘");
        map.put("3","风扇");
        return map;
    }

    //查询商品列表
    @RequestMapping("/queryItemsList.action")
    public ModelAndView queryItemsList(/*HttpServletRequest request*/){
        //System.out.println("测试重定向是否携带数据id = "+request.getParameter("id"));
        List<Items> itemsList = itemsService.selectList();
        ModelAndView mv = new ModelAndView();
        //将数据添加到请求中
        mv.addObject("itemsList",itemsList);
        //添加逻辑视图名
        mv.setViewName("itemsList.jsp");
        return mv;
    }


    /**
     * 查询单个商品
     * @param id 简单类型的参数绑定：页面中请求参数传递至controller中时，要求请求参数的key必须与形参名称一致
     * @param model：直接传递数据至页面上
     * @return String 返回的是逻辑视图名
     *
     * 限制http请求方法  method = RequestMethod.POST
     * @RequestParam(value = "id",required = true)  确保请求域中的key与value的值一致，完成参数绑定，required一旦为true，绑定不成功则直接报错
     */
    @RequestMapping(value = "/queryItems.action")
    public String queryItems(Integer id, Model model) throws CustomException {
        Items items = itemsService.selectOne(id);
        //传递数据至页面
        model.addAttribute("items",items);

        //数学异常，测试了非自定义的异常情况
        //int i = 10/0;

        //返回的逻辑视图名
        return "editItem.jsp";
    }

    /**
     * 修改商品
     * @param items  pojo类型的参数绑定：form表单中input的name必须与想要绑定的pojo中属性名一致
     *
     * @Validated Items items , BindingResult bindingResult  完成校验当前商品信息
     * @Validated(value = ValidateGroup1.class) 当前支持校验分组1中的校验规则
     *
     * 回显功能
     * 1.SpringMVC 自带回显功能，要求页面中${key}的Key必须是pojo的类名首字母小写
     * 2.model.addAttribute("item",items); 再次添加至请求域中
     * 3.@ModelAttribute("item") 通过注解，再次添加至请求域中，与页面中${key}的key保持一致
     *
     * 文件上传
     * MultipartFile pictureFile，为了形成参数绑定，要求形参的名称必须与input中type为file的name是一致的
     */
    @RequestMapping("/updateItems.action")
    public String updateItems(@Validated(value = {ValidateGroup1.class}) /*@ModelAttribute("item")*/ Items items ,
                              BindingResult bindingResult ,
                              Model model,
                              MultipartFile pictureFile
                             /*,Integer id,HttpServletRequest request*/) throws IOException {
        //request.setAttribute("id",id);

        //获取到校验信息，若存在错误，则显示到页面展示
        if(bindingResult!=null && bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError allError : allErrors) {
                System.out.println("错误信息："+allError.getDefaultMessage());
            }
            model.addAttribute("allErrors",allErrors);

            //失败  数据回显
            // model.addAttribute("item",items);
            return "editItem.jsp";
        }


        //上传
        if(pictureFile!=null){
            String oldName = pictureFile.getOriginalFilename();
            System.out.println("oldName = "+oldName);
            //真正上传图片
            if(oldName!=null && oldName.indexOf(".")>0){
                String newName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));

                //将图片存储到服务器上  E:\8.SSM\temp
                pictureFile.transferTo(new File("E:\\8.SSM\\temp\\"+newName));

                //将图片给商品传入
                items.setPic("/pic/"+newName);
            }
        }

        //更新
        itemsService.update(items);
        /**
         * 响应重定向和请求转发的区别？
         * 1.响应重定向 redirect  ； 请求转发 forward。
         * 2.响应重定向，地址栏会发生变化，显示目标地址，响应重定向避免重复提交表单；
         * 请求转发，地址栏不会发生变化，显示请求地址。
         * 3.响应重定向会生成新的请求，无法携带数据；
         * 请求转发延续之前的请求，可以携带数据。
         * 4.响应重定向可以重定向到任何页面，但是请求转发只能转发至本项目中的页面
         */
        //成功 重定向 商品列表页面
        return "redirect:queryItemsList.action";

        //请求转发
        //return "forward:queryItemsList.action";
    }

    /**
     * 根据商品名称模糊查询
     * 包装类型pojo的参数绑定：与包装类型pojo形成参数绑定ItemsQueryVO，页面中input的name必须是pojo的关联的属性去级联操作itemsCustom.name
     */
    @RequestMapping("/findItemsList.action")
    public String findItemsList(ItemsQueryVO vo,Model model){
        List<ItemsCustom> list = itemsService.selectListByLikeName(vo);
        model.addAttribute("itemsList",list);
        return "itemsList.jsp";
    }

    /**
     * 批量删除
     * 数组的参数绑定：页面中input类型是checkbox的name必须与形参中名称一致
     */
    @RequestMapping("/deleteAll.action")
    public String deleteAll(Integer[] ids){
        itemsService.deleteAll(ids);
        return "redirect:queryItemsList.action";
    }

    /**
     * 进入批量修改页面 editItemsList
     */
    @RequestMapping("/editItemsList.action")
    public String editItemsList(Model model){
        List<Items> itemsList = itemsService.selectList();
        model.addAttribute("itemsList",itemsList);
        return "editItemsList.jsp";
    }

    /**
     * 进入批量修改页面 editItemsMap
     */
    @RequestMapping("/editItemsMap.action")
    public String editItemsMap(Model model){
        List<Items> itemsList = itemsService.selectList();
        model.addAttribute("itemsList",itemsList);
        return "editItemsMap.jsp";
    }

    /**
     * 批量修改  List
     * 集合List的参数绑定：包装的pojo中关联的集合，与页面中input的name一致 itemsCustomList[下标].属性
     */
    @RequestMapping("/updateAll.action")
    public String updateAll(ItemsQueryVO vo){
        List<ItemsCustom> itemsCustomList = vo.getItemsCustomList();
        itemsService.updateAll(itemsCustomList);
        return "redirect:queryItemsList.action";
    }

    /**
     * 批量修改  Map
     * 集合Map的参数绑定：包装的pojo中关联的集合，与页面中input的name一致 map[下标].属性
     */
    @RequestMapping("/updateAllMap.action")
    public String updateAllMap(ItemsQueryVO vo){
        Map<String, ItemsCustom> map = vo.getMap();
        itemsService.updateAllMap(map);
        return "redirect:queryItemsList.action";
    }

}
