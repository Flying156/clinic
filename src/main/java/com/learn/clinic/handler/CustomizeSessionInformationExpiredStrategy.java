package com.learn.clinic.handler;

import com.alibaba.fastjson2.JSON;
import com.learn.clinic.common.ResultEnum;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 会话信息过期处理
 *
 * @author Milk
 * @version 2023/12/27 15:33
 */
@Component
public class CustomizeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        Result result = Results.fail(ResultEnum.USER_ACCOUNT_USE_BY_OTHERS);
        HttpServletResponse response = event.getResponse();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
