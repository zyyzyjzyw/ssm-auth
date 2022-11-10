package com.tedu.java;

import com.tedu.java.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author： zyy
 * @date： 2022/11/6 21:38
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@SpringBootTest
public class TokenTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testToken(){
        String token = JwtHelper.createToken("1","admin");
        System.out.println(token);
    }

    @Test
    void redis(){
        redisTemplate.opsForValue().set("name","this is my redis and springboot");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }


}
