package com.geekaca.mall.exceptions;

/**
 * 用户名已经被占用异常
 */
public class LoginNameExsistsException extends RuntimeException{
    public LoginNameExsistsException(String msg) {
        super(msg);
    }
}
