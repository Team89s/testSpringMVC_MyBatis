package com.igeek.ssm.service;

import com.igeek.ssm.dao.ItemsCustomMapper;
import com.igeek.ssm.dao.ItemsMapper;
import com.igeek.ssm.pojo.Items;
import com.igeek.ssm.pojo.ItemsCustom;
import com.igeek.ssm.vo.ItemsQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsCustomMapper itemsCustomMapper;

    //查询商品列表
    @Transactional(readOnly = true)
    public List<Items> selectList(){
        List<Items> itemsList = itemsMapper.selectByExampleWithBLOBs(null);
        return itemsList;
    }

    //查询单个商品
    @Transactional(readOnly = true)
    public Items selectOne(Integer id){
        Items items = itemsMapper.selectByPrimaryKey(id);
        return items;
    }

    //修改商品
    public int update(Items items){
        int i = itemsMapper.updateByPrimaryKeySelective(items);
        return i;
    }

    //根据商品名称模糊查询商品列表
    public List<ItemsCustom> selectListByLikeName(ItemsQueryVO vo){
        return itemsCustomMapper.findItemsByLikeName(vo);
    }

    //批量删除
    public void deleteAll(Integer[] ids){
        for (Integer id : ids) {
            itemsMapper.deleteByPrimaryKey(id);
        }
    }

    //批量修改
    public void updateAll(List<ItemsCustom> itemsList){
        for (ItemsCustom itemsCustom : itemsList) {
            itemsMapper.updateByPrimaryKeySelective(itemsCustom);
        }
    }
}
