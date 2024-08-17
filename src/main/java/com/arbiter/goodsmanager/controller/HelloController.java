package com.arbiter.goodsmanager.controller;

import com.arbiter.goodsmanager.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class HelloController {
    private final UserService userService;
    @RequestMapping("/hello")
    public String hello()
    {
        return userService.findAll().toString();
    }

}
