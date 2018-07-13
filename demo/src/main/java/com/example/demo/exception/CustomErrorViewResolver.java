package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * ErrorViewResolver
 *
 * @author Fluky
 * @create 2018-07-12 15:46
 **/
public class CustomErrorViewResolver implements ErrorViewResolver {
    private static final Logger log=LoggerFactory.getLogger(CustomErrorViewResolver.class);
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus httpStatus, Map<String, Object> map) {
        log.error( "zhes ge :"+request.getLocalAddr());

        return null;
    }
}
