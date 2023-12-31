package com.learn.clinic.handler;

import com.alibaba.fastjson2.JSON;
import com.learn.clinic.common.ResultEnum;
import com.learn.clinic.expection.CaptchaException;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败
 *
 * @author Milk
 * @version 2023/12/27 10:59
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Result result = null;
        if (exception instanceof AccountExpiredException) {
            //账号过期
            result = Results.fail(ResultEnum.USER_ACCOUNT_EXPIRED);
        } else if (exception instanceof BadCredentialsException) {
            //密码错误
            result = Results.fail(ResultEnum.USER_CREDENTIALS_ERROR);
        } else if (exception instanceof CredentialsExpiredException) {
            //密码过期
            result = Results.fail(ResultEnum.USER_CREDENTIALS_EXPIRED);
        } else if (exception instanceof DisabledException) {
            //账号不可用
            result = Results.fail(ResultEnum.USER_ACCOUNT_DISABLE);
        } else if (exception instanceof LockedException) {
            //账号锁定
            result = Results.fail(ResultEnum.USER_ACCOUNT_LOCKED);
        } else if (exception instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = Results.fail(ResultEnum.USER_ACCOUNT_NOT_EXIST);
        }else if(exception instanceof CaptchaException){
            result = Results.fail(ResultEnum.VERIFICATION_CODE_ERROR);
        }else{
            //其他错误
            result = Results.fail(ResultEnum.COMMON_FAIL);
        }

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
