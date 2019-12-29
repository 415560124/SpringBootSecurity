package com.rhy.security.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Herion_Rhy
 * @Description:
 * @Date: Created in 2019/12/29 13:40
 * @Modified By:
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("user")
public class UserController {
    @PostMapping("welcome")
    public Map<String,Object> welcome(){
        Map<String,Object> res = new HashMap<>();
        res.put("name","welcome");
        return res;
    }
    @PostMapping("details")
    public Map<String,Object> details(){
        Map<String,Object> res = new HashMap<>();
        res.put("name","details");
        return res;
    }
    @PostMapping("change")
    public Map<String,Object> change(){
        Map<String,Object> res = new HashMap<>();
        res.put("name","change");
        return res;
    }
}
