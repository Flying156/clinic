package com.learn.clinic.handler;

import com.alibaba.fastjson2.JSON;
import com.learn.clinic.uitls.JwtUtil;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理
 *
 * @author Milk
 * @version 2023/12/27 15:29
 */
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result result = Results.success();
        // 将 jwt token 设为空
        response.setHeader(JwtUtil.HEADER, "");
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
