package com.zhuimeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/8/9.
 */
@Controller
public class HelloController {
    @RequestMapping("/")
    public String index() {
        return "redirect:/getUsers";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
