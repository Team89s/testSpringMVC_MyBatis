package com.igeek.ssm.service;

import com.igeek.ssm.dao.ItemsMapper;
import com.igeek.ssm.pojo.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    //查询商品列表
    @Transactional
    public List<Items> selectList(){
        List<Items> itemsList = itemsMapper.selectByExampleWithBLOBs(null);
        return itemsList;
    }

}
