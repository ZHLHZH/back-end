package com.example.xyshw.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Configuration
public class Session{

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value, 20, TimeUnit.MINUTES);
    }

    public String get(String key) {
        if (!stringRedisTemplate.hasKey(key)) {
            return null;
        }
        return stringRedisTemplate.opsForValue().get(key);
    }
}