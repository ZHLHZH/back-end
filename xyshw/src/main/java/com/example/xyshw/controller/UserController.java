package com.example.xyshw.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.xyshw.mapper.UserMapper;
import com.example.xyshw.model.Result;
import com.example.xyshw.model.User;
import com.example.xyshw.session.Session;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.data.redis.core.StringRedisTemplate;
=======
>>>>>>> ce8f1b32aba4bdbc4f31f9b0e507efa2d4202d67
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    Session session;

    @Autowired
    private UserMapper userMapper;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        int code = 200;
        Object data = null;
        String message = "success";

        if (user.getUserName() == null) {
            code = 4401;
            message = "用户名为空";
        }
        else if (userMapper.selectIdByUserName(user.getUserName()) != null){
            code = 4402;
            message = "用户名已存在";
        }
        else if(user.getPassWord() == null) {
            code = 4403;
            message = "密码为空";
        }
        else {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insert(user);
        }

        return JSON.toJSONString(new Result(code, data, message));
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        String userName = user.getUserName();
        String passWord = user.getPassWord();

        if (userMapper.selectIdByUserName(userName) == null) {
            return JSON.toJSONString(new Result(4404, null, "用户名不存在"));
        }
        if (!userMapper.selectPaddWordByUserName(userName).equals(passWord)) {
            return JSON.toJSONString(new Result(4405, null, "密码错误"));
        }
        String sessionId = UUID.randomUUID().toString();
        session.set(userName, sessionId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionId",sessionId);
        return JSON.toJSONString(new Result(200, jsonObject, "success"));
    }

    @PostMapping("/test")
    public void test(@RequestBody User user, HttpServletRequest request) {
        System.out.println(user.toString());
        System.out.println(request.getCookies()[0].getValue());
    }


}
