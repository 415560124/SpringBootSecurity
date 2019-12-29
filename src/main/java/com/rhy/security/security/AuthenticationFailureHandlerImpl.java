package com.rhy.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Herion_Rhy
 * @Description: 登陆失败时处理
 * @Date: Created in 2019/12/29 11:40
 * @Modified By:
 * @Version: 1.0.0
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //异常具体信息应该根据这个写入登录失败的提示信息
        exception.printStackTrace();
        //写入登录失败相应内容
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String,Object> res = new HashMap<>();
        res.put("code","100205");
        res.put("data",exception.getMessage());
        res.put("msg","登录失败");
        response.getWriter().write(objectMapper.writeValueAsString(res));
    }
}
