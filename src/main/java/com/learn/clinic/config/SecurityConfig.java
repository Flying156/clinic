package com.learn.clinic.config;

import com.learn.clinic.handler.*;
import com.learn.clinic.service.Impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 配置
 *
 * @author Milk
 * @version 2023/12/26 20:44
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userService;
    private final CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomizeAuthenticationFailureHandler failureHandler;
    private final CustomizeAuthenticationSuccessHandler successHandler;
    private final CustomizeLogoutSuccessHandler logoutSuccessHandler;
    private final CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    private final CustomizeAccessDeniedHandler accessDeniedHandler;
    private final CaptchaFilter captchaFilter;
    private static final String[] URL_WHITELIST = {
            "/captcha",
    };


    /**
     * 密码加密器加载
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                // 权限认证
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()
                .antMatchers("/getDrug").hasAuthority("manage_drug")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                // 登录
                .and()
                .formLogin()
                .permitAll() // 允许所有用户
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                // 登出
                .and()
                .logout()
                .permitAll()
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID") // 删除所有 cookie
                // 添加过滤器
                .and()
                .addFilter(jwtAuthenticationFilter())
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);
                // 会话管理

    }

}
