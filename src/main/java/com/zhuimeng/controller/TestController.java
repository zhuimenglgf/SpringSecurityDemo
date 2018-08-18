package com.zhuimeng.controller;

import com.zhuimeng.pojo.Users;
import com.zhuimeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/8/8.
 */
@RestController
public class TestController {
    @Autowired
    private UserService userService;

    @RequestMapping("getUsers")
    @ResponseBody
    public Users getUsers(){
        return userService.selectByPrimaryKey(1);
    }
}
