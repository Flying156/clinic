package com.learn.clinic.expection;

import org.springframework.security.core.AuthenticationException;

/**
 * JWT token 异常
 *
 * @author Milk
 * @version 2023/12/30 21:05
 */
public class JwtException extends AuthenticationException {

    public JwtException(String msg) {
        super(msg);
    }

}
