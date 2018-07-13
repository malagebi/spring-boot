package com.example.demo.exception;

/**
 * CustomerException
 *
 * @author Fluky
 * @create 2018-07-12 13:50
 **/
public class CustomerException extends Exception  {
    private  String   code;
    private String  message;

    public CustomerException() {
        super();
    }

    public CustomerException(String message) {
        super(message);
        this.message = message;
    }

    public CustomerException( String code,String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
