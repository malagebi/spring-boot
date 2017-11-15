package com.example.demo.service;

import com.example.demo.dao.model.UserInfo;

/**
 * @author lishunli
 * @create 2017-11-14 13:12
 **/
public interface UserService {
   UserInfo findUserByLoginName();
}
