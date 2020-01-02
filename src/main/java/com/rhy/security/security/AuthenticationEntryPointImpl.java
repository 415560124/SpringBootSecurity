package com.rhy.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Herion_Rhy
 * @Description: 身份校验失败处理器，如 token 错误
 * @Date: Created in 2020/1/1 20:56
 * @Modified By:
 * @Version: 1.0.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //异常具体信息应该根据这个写入登录失败的提示信息
        authException.printStackTrace();
        //写入登录失败相应内容
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String,Object> res = new HashMap<>();
        res.put("code","100400");
        res.put("data",authException.getMessage());
        res.put("msg","token错误");
        response.getWriter().write(objectMapper.writeValueAsString(res));
    }
}
