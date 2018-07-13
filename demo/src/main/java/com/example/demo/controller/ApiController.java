package com.example.demo.controller;

import com.example.demo.entity.UserInfo;
import com.example.demo.service.RedisService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * API
 *
 * @author Fluky
 * @create 2018-07-12 12:54
 **/
@RestController
@RequestMapping(value = "/api")
public class ApiController {


    @Resource
    private RedisService redisService;
    @Resource
    private UserService userService;

    @GetMapping(value = "/v1/users")
    public List<UserInfo> getUserList(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {
        return userService.findUsers(pageNumber, pageSize);
    }

    @PostMapping(value = "/v1/user", consumes = "application/json;charset=utf-8")
    public Object addUser(@RequestBody UserInfo info) {
        userService.saveUser(info);
        return info;
    }
}
