package com.rhy.security;

import com.rhy.security.mapper.IFunctionMapper;
import com.rhy.security.security.JwtTokenUtil;
import com.rhy.security.security.JwtUserImpl;
import com.rhy.security.security.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SecurityApplicationTests {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;
    @Test
    void contextLoads() {
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername("admin");
        System.out.println(userDetails);
        String token = jwtTokenUtil.createToken(userDetails);
        System.out.println(token);
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        System.out.println(userName);
    }

}
