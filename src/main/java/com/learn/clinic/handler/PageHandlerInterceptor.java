package com.learn.clinic.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.clinic.uitls.PageUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 分页拦截器
 *
 * @author Milk
 * @version 2023/12/28 10:32
 */
@Component
public class PageHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String current = request.getParameter("current");
        if(StrUtil.isNotBlank(current)){
            String size = Optional.ofNullable(
                    request.getParameter("size")).orElse("10");
            PageUtils.setPage(Page.of(Long.parseLong(current), Long.parseLong(size)));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        PageUtils.removePage();
    }
}
