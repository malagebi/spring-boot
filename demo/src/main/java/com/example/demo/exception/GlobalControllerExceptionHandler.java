package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lishunli
 * @create 2017-11-14 15:41
 **/
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    /**
     * 运行时异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView processException(RuntimeException exception) {
        logger.info("自定义异常处理-RuntimeException");
        ModelAndView m = new ModelAndView();
        m.addObject("exception", exception.getMessage());
        m.setViewName("error/500");
        return m;

    }

    /**
     * Excepiton异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<Object> processException(HttpServletRequest request,Exception ex) {
        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "自定义异常处理-Exception");

        if (ex instanceof HttpRequestMethodNotSupportedException) {
            Error MethodNot = new Error(HttpStatus.METHOD_NOT_ALLOWED.value(), "Request method "+ request.getMethod()+" not supported");
            return new ResponseEntity<Object>(MethodNot, HttpStatus.METHOD_NOT_ALLOWED);
        }

        if (ex instanceof NoHandlerFoundException) {
            Error noHandlerFound = new Error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
            return new ResponseEntity<Object>(noHandlerFound, HttpStatus.NOT_FOUND);
        }
        logger.info("自定义异常处理-Exception");
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public ResponseEntity<Object> processException(ConstraintViolationException exception) {
        logger.info("校验处理-ConstraintViolationException");
        Error error = new Error(400, "参数异常");
        List<Error> errorList = new ArrayList<>();
        errorList.add(error);
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setErrors(errorList);

        return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
    }

}
