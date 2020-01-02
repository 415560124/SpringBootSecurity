package com.rhy.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Herion_Rhy
 * @Description:
 * @Date: Created in 2019/12/31 15:45
 * @Modified By:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping("sesssionDemo")
    public Map<String,Object> sessionDemo(HttpSession httpSession){
        Map<String,Object> res = new HashMap<>();
        res.put("session",httpSession);
        return res;
    }
}
