package com.example.demo.controller;

import com.example.demo.MailService;
import com.example.demo.config.SystemProperties;
import com.example.demo.entity.DataResult;
import com.example.demo.entity.UserInfo;
import com.example.demo.i18n.LocaleService;
import com.example.demo.queue.MessageSender;
import com.example.demo.service.UserService;
import com.example.demo.utils.RedisUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final SystemProperties properties;

    @Autowired
    public IndexController(SystemProperties properties) {
        this.properties = properties;
    }

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
    @Autowired
    private RedisManager redisManager;

    @Autowired
    @Qualifier("redisObjectTemplate")
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    @Qualifier("redisStringTemplate")
    private RedisTemplate<String, String> redisStringTemplate;


    @RequestMapping("/index")
    public String hello(Model model) {
        redisStringTemplate.opsForValue().set("aaa", "111");
        return "ok";
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserInfo user = new UserInfo();
        user.setUserName("admin");
        subject.getSession().setAttribute("user", user);

        return "success";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @RequiresPermissions(value = "user:list")
    public String logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();

        subject.logout();
        return "loginPage";
    }

    @RequestMapping(value = "/loginPage")
    public String loginPage() {
        return "login";
    }
}
