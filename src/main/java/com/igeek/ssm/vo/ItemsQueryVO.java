package com.igeek.ssm.vo;

import com.igeek.ssm.pojo.ItemsCustom;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Description ItemsQueryVO  封装商品的查询条件
 * @Author chenmin
 * @Date 2021/3/9 15:55
 */
public class ItemsQueryVO {

    //综合查询条件 商品名称模糊查询
    private ItemsCustom itemsCustom;

    //批量更新商品的信息
    private List<ItemsCustom> itemsCustomList;

    //批量更新商品的信息
    private Map<String,ItemsCustom> map;


    public ItemsQueryVO() {
    }

    public ItemsQueryVO(ItemsCustom itemsCustom, List<ItemsCustom> itemsCustomList, Map<String, ItemsCustom> map) {
        this.itemsCustom = itemsCustom;
        this.itemsCustomList = itemsCustomList;
        this.map = map;
    }

    /**
     * 获取
     * @return itemsCustom
     */
    public ItemsCustom getItemsCustom() {
        return itemsCustom;
    }

    /**
     * 设置
     * @param itemsCustom
     */
    public void setItemsCustom(ItemsCustom itemsCustom) {
        this.itemsCustom = itemsCustom;
    }

    /**
     * 获取
     * @return itemsCustomList
     */
    public List<ItemsCustom> getItemsCustomList() {
        return itemsCustomList;
    }

    /**
     * 设置
     * @param itemsCustomList
     */
    public void setItemsCustomList(List<ItemsCustom> itemsCustomList) {
        this.itemsCustomList = itemsCustomList;
    }

    /**
     * 获取
     * @return map
     */
    public Map<String, ItemsCustom> getMap() {
        return map;
    }

    /**
     * 设置
     * @param map
     */
    public void setMap(Map<String, ItemsCustom> map) {
        this.map = map;
    }

    public String toString() {
        return "ItemsQueryVO{itemsCustom = " + itemsCustom + ", itemsCustomList = " + itemsCustomList + ", map = " + map + "}";
    }
}
