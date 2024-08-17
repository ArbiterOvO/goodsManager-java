package com.arbiter.goodsmanager;

import com.arbiter.goodsmanager.pojo.User;
import com.arbiter.goodsmanager.properties.JwtProperties;
import com.arbiter.goodsmanager.service.user.UserService;
import com.arbiter.goodsmanager.util.JwtUtil;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest

class GoodsManagerApplicationTests {

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        List<User> all = userService.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

    @Test
    void jwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 123);
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        System.out.println(token);
        Claims claims1 = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
        Long userId = Long.valueOf(claims1.get("id").toString());
        System.out.println(userId);
    }

}
