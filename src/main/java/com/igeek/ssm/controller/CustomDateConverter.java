package com.igeek.ssm.controller;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @Description CustomDateConverter 自定义的日期绑定器
 * @Author chenmin
 * @Date 2021/3/9 15:25
 */
public class CustomDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        Date date = null;
        if(s!=null && !s.equals("")){
            //将String -->  Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
}
