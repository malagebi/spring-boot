package com.example.demo.service.impl;

import com.example.demo.dao.mapper.UserInfoMapper;
import com.example.demo.dao.model.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author lishunli
 * @create 2017-11-14 13:13
 **/
@Service
public class UserServiceImpl implements UserService{
    @Autowired
     public  UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findUserByLoginName() {
        //PageHelper.startPage(1,10);
        return  userInfoMapper.selectByPrimaryKey(1L);
    }
}
