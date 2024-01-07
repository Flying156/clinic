package com.learn.clinic.handler;

import cn.hutool.core.util.StrUtil;
import com.learn.clinic.expection.JwtException;
import com.learn.clinic.service.Impl.UserDetailsServiceImpl;
import com.learn.clinic.uitls.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 过滤器
 *
 * @author Milk
 * @version 2023/12/27 15:17
 */
//@Component
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Resource
    private  JwtUtil jwtUtil;

    @Resource
    private  UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(JwtUtil.HEADER);
        if (StrUtil.isBlankOrUndefined(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = jwtUtil.getClaimsByToken(token);
        if(claims == null){
            throw new JwtException("token 异常");
        }
        if(jwtUtil.isTokenExpired(claims.getExpiration())){
            throw new JwtException("token 已过期");
        }
        String username = claims.getSubject();
        User user = (User) userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
