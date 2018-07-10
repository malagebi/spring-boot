package com.example.demo.security;

import com.example.demo.entity.UserInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro
 *
 * @author lishunli
 * @create 2017-11-13 10:52
 **/
public class ShiroSecurity extends AuthorizingRealm {

    /**
     * 用于获取登录成功后的角色、权限等信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        authorizationInfo.addRoles(roles);

        Set<String> perissions = new HashSet<>();
        perissions.add("user:list");
        authorizationInfo.setStringPermissions(perissions);
        return authorizationInfo;
    }


    /**
     * 验证当前登录的Subject
     *
     * @param
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        UserInfo  user=new  UserInfo();
        user.setUserId(usernamePasswordToken.getUsername());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, "admin", getName());
        return info;
    }
}
