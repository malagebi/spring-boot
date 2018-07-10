package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 123
 *
 * @author Fluky
 * @create 2018-03-26 15:38
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DempTest {
    @Resource(name="redisStringTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name="redisObjectTemplate")
    private RedisTemplate redisTemplate;

    @Test
    public void findOne() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
    }
}
