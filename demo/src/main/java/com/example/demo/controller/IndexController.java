package com.example.demo.controller;

import com.example.demo.MailService;
import com.example.demo.entity.DataResult;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 首页
 *
 * @author lishunli
 * @create 2017-11-10 10:35
 **/
@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    private String to = "shunli.li@ngaa.com.cn";


    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;


    @RequestMapping("/index")
    public String hello(Model model) {

        return "index";
    }


    @ApiOperation(value = "获取用户列表", notes = "获取全部")
    @ApiImplicitParam(name = "id", value = "用户ID")
    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public UserInfo hello() {
        UserInfo entity = new UserInfo();
        entity.setId(1L);
        entity.setUserName("张三");
        //entity.setPwd(HttpUtil.instatic().senfGet("https://www.baidu.com"));
        return entity;
    }


    @RequestMapping("/email")
    public String email() {
        mailService.sendHtmlMail(to, "top", "测试HTML");
        return "redirect:/index";
    }

    @RequestMapping("/page")
    //@RequiresPermissions(value = "user:list")
    @RequiresRoles(value = "admin")
    public ModelAndView page() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping("/userList")
    public DataResult userList(@RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false, defaultValue = "10") int rows, Model model) {
        List<UserInfo> list = userService.findUserByLoginName();
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(list);
        DataResult result = new DataResult();
        result.setRows(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }


    //shiro 登录页跳转
    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }


    //登录方法
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserInfo user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //return "success";
        return "redirect:/index";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @RequiresPermissions(value = "user:list")
    public String logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();

        subject.logout();
        return "loginPage";
    }


    @RequestMapping(value = "/saveUser")
    public ModelAndView saveUser() {
        UserInfo user = new UserInfo();
        user.setUserName("test");
        user.setPassword("123");
        userService.saveUser(user);
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
