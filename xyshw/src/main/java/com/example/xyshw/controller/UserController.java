package com.example.xyshw.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.xyshw.mapper.UserMapper;
import com.example.xyshw.model.Result;
import com.example.xyshw.model.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public String login(@RequestBody User user) {
        int code = 200;
        Object data = null;
        String message = "success";

        if (user.getUserName() == null) {
            code = 4401;
            message = "用户名为空";
        }
        else if (!userMapper.selectIdByUserName(user.getUserName()).isEmpty()){
            code = 4402;
            message = "用户名已存在";
        }
        else if(user.getPassWord() == null) {
            code = 4402;
            message = "密码为空";
        }
        else {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insert(user);
        }

        return JSON.toJSONString(new Result(code, data, message));
    }


}
