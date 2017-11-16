package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.MailService;
import com.example.demo.config.SystemProperties;
import com.example.demo.dao.model.UserInfo;
import com.example.demo.i18n.LocaleService;
import com.example.demo.queue.MessageSender;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 首页
 *
 * @author lishunli
 * @create 2017-11-10 10:35
 **/
@RestController
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    private String to = "shunli.li@ngaa.com.cn";
    @Autowired
    private SystemProperties properties;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MailService mailService;
    @Autowired
    private MessageSender messageSender;

    @Autowired
    private LocaleService localeService;
    @Autowired
    private UserService userService;


    @RequestMapping("/index")
    public ModelAndView hello(Map<String, Object> map) {

        String msg2 = localeService.getMessage("welcome");
        map.put("name", msg2);
        UserInfo entity = userService.findUserByLoginName();
        log.info(JSON.toJSONString(entity));
        map.put("path", JSON.toJSONString(entity));
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping("/api/test")
    public UserInfo hello() {
        UserInfo entity = new UserInfo();
        entity.setId(1L);
        entity.setUserName("张三");
        return entity;
    }

    @RequestMapping("/rabbitmq")
    public void rabbitmq() {
        messageSender.send();
    }

    @RequestMapping("/email")
    public String email() {
        mailService.sendHtmlMail(to, "top", "测试HTML");
        return "redirect:/index";
    }

}
