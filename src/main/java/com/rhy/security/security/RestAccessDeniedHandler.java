package com.rhy.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Herion_Rhy
 * @Description:
 * @Date: Created in 2020/1/1 21:35
 * @Modified By:
 * @Version: 1.0.0
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //异常具体信息应该根据这个写入登录失败的提示信息
        accessDeniedException.printStackTrace();
        //写入登录失败相应内容
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String,Object> res = new HashMap<>();
        res.put("code","100400");
        res.put("data",accessDeniedException.getMessage());
        res.put("msg","权限不足");
        response.getWriter().write(objectMapper.writeValueAsString(res));
    }
}
