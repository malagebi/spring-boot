package com.example.demo.service.impl;

import com.example.demo.dao.mapper.UserInfoMapper;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author lishunli
 * @create 2017-11-14 13:13
 **/
@Service
public class UserServiceImpl implements UserService{
    @Autowired
     public  UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> findUserByLoginName() {
        PageHelper.startPage(1,10);
        return  userInfoMapper.selectByPrimaryKey(1L);
    }
}
