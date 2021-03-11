package com.igeek.ssm.service;

import com.igeek.ssm.dao.UserMapper;
import com.igeek.ssm.pojo.User;
import com.igeek.ssm.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @Description TODO
 * @Author chenmin
 * @Date 2021/3/11 15:34
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //通过姓名和地址查询用户信息
    public User login(String username,String address){
        User user = null;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andAddressEqualTo(address);
        List<User> users = userMapper.selectByExample(userExample);
        if(users!=null){
            user = users.get(0);
            if(user!=null){
                return user;
            }
        }
        return user;
    }

    //查询列表
    public List<User> selectUserList(){
        List<User> users = userMapper.selectByExample(null);
        return users;
    }

    //查询单个用户信息
    public User selectUserOne(Integer id){
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    //增加
    public void insertUser(User user){
        userMapper.insert(user);
    }

    //更新
    public void updateUser(User user){
        userMapper.updateByPrimaryKeySelective(user);
    }

    //删除
    public void deleteUser(Integer id){
        userMapper.deleteByPrimaryKey(id);
    }

}
