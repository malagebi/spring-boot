package com.example.demo.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author lishunli
 * @create 2017-11-20 15:29
 **/
public class KickoutSessionControlFilter extends AccessControlFilter {


    //isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);

            if(subject.getPrincipal() != null){
                // If principal is not null, then the user is known and should be allowed access.
                if(subject.getSession().getAttribute("user") == null){
                    //如果session失效重新赋值，用于修复启用remember me功能后，重开浏览器session失效的问题
                    subject.getSession().setAttribute("user", subject.getPrincipal());
                }
                return true;
            }else{
                return false;
            }
        }
    }

    //onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }


}
