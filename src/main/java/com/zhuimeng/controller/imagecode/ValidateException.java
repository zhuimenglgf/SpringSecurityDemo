package com.zhuimeng.controller.imagecode;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Administrator on 2018/8/14.
 */
public class ValidateException extends AuthenticationException {

    public ValidateException(String msg) {
        super(msg);
    }
}
