package com.example.demo.filter;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lishunli
 * @create 2017-11-16 14:58
 **/
public class RateLimiterFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(RateLimiterFilter.class);
    private RateLimiter rateLimiter = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("come in RateLimiterFilter-------------------------");
        //首先申请一个容量为1(每秒)的限流器，
        rateLimiter = RateLimiter.create(1);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("start in RateLimiterFilter-------------------------");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if (rateLimiter.tryAcquire()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //req.getRequestDispatcher("/404").forward(req, res);
            res.sendRedirect(req.getContextPath() + "/index");
        }

    }

    @Override
    public void destroy() {

    }
}
