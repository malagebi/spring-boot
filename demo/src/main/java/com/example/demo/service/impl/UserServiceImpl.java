package com.example.demo.service.impl;

import com.example.demo.dao.mapper.UserInfoMapper;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author lishunli
 * @create 2017-11-14 13:13
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> findUserByLoginName() {
        PageHelper.startPage(1, 10);
        return userInfoMapper.selectByPrimaryKey(1L);
    }

    @Override
    @Transactional
    public void saveUser(UserInfo user) {
        userInfoMapper.insert(user);

    }

    @Override
    @Cacheable(value = "users",key = "#p0")
    public List<UserInfo> findUsers(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        return userInfoMapper.findUsers();
    }
}
