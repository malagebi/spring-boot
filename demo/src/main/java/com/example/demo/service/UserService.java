package com.example.demo.service;

import com.example.demo.entity.UserInfo;

import java.util.List;

/**
 * @author lishunli
 * @create 2017-11-14 13:12
 **/
public interface UserService {
    List<UserInfo> findUserByLoginName();
    void saveUser(UserInfo  user);
    List<UserInfo> findUsers(int pageNumber,int pageSize);
}
