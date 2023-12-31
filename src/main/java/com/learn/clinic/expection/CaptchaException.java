package com.learn.clinic.expection;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码错误异常
 *
 * @author Milk
 * @version 2023/12/30 20:17
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg) {
        super(msg);
    }
}
