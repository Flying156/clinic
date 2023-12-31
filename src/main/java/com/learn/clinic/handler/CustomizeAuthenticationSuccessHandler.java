package com.learn.clinic.handler;

import com.alibaba.fastjson2.JSON;
import com.learn.clinic.common.ResultEnum;
import com.learn.clinic.uitls.JwtUtil;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录成功
 *
 * @author Milk
 * @version 2023/12/27 10:54
 */
@Component
@RequiredArgsConstructor
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Result result = Results.success(ResultEnum.SUCCESS);

        String jwtToken = jwtUtil.generateToken(authentication.getName());
        response.setHeader(JwtUtil.HEADER, jwtToken);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
