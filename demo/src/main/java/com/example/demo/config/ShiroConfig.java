package com.example.demo.config;

import com.example.demo.security.ShiroSecurity;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro
 *
 * @author lishunli
 * @create 2017-11-13 10:50
 **/
@Configuration
public class ShiroConfig  {
    private static final Logger log = LoggerFactory.getLogger(ShiroConfig.class);


    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager ) {
        log.info("come in shiroFilter----------------------------------");
        ShiroFilterFactoryBean  shiroFilterFactoryBean = new ShiroFilterFactoryBean ();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        /* 配置不会被拦截的链接 顺序判断 */
        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "anon");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public ShiroSecurity shiroRealm(){
        ShiroSecurity shiroRealm = new ShiroSecurity();

        return shiroRealm;
    }


    @Bean
    public DefaultWebSecurityManager  securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
                UsernamePasswordToken utoken=(UsernamePasswordToken) token;
                //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
                String inPassword = new String(utoken.getPassword());
                //获得数据库中的密码
                String dbPassword=(String) info.getCredentials();
                //进行密码的比对
                //return this.equals(inPassword, dbPassword);
                return false;
            }
        };
    }

    /**
     * 负责shiroBean的生命周期
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
}
