package com.learn.clinic.handler;

import cn.hutool.core.util.StrUtil;
import com.learn.clinic.expection.CaptchaException;
import com.learn.clinic.uitls.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 *
 * @author Milk
 * @version 2023/12/30 20:10
 */
@Component
@RequiredArgsConstructor
public class CaptchaFilter extends OncePerRequestFilter {

    private final CustomizeAuthenticationFailureHandler failureHandler;
    private final RedisUtil redisUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        if("/login".equals(url) && request.getMethod().equals("POST")){
            try{
                validate(request);
                filterChain.doFilter(request, response);
            }catch (CaptchaException e){
                failureHandler.onAuthenticationFailure(request, response, e);
            }
        }else{
            filterChain.doFilter(request, response);
        }
    }

    /**
     * 验证码校验
     *
     * @param request 登录请求
     */
    private void validate(HttpServletRequest request) {
        String code = request.getParameter("code");
        String uuid = request.getParameter("uuid");

        if(StrUtil.isBlank(code) || StrUtil.isBlank(uuid)){
            throw new CaptchaException("验证码为空");
        }

        String getCode = (String) redisUtil.get("code", uuid);
        if(!code.equals(getCode)){
            throw new CaptchaException("验证码错误");
        }else {
            redisUtil.del("code");
        }

    }
}
