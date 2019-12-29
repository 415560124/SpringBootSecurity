package com.rhy.security;

import com.rhy.security.mapper.IFunctionMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SecurityApplicationTests {
    @Autowired
    IFunctionMapper iFunctionMapper;
    @Test
    void contextLoads() {
        System.out.println(iFunctionMapper.selectAll());
    }

}
