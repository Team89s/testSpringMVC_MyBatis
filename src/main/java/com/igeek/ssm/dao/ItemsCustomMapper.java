package com.igeek.ssm.dao;

import com.igeek.ssm.pojo.ItemsCustom;
import com.igeek.ssm.vo.ItemsQueryVO;

import java.util.List;

/**
 * @version 1.0
 * @Description TODO
 * @Author chenmin
 * @Date 2021/3/9 15:57
 */
public interface ItemsCustomMapper {

    public List<ItemsCustom> findItemsByLikeName(ItemsQueryVO vo);

}
