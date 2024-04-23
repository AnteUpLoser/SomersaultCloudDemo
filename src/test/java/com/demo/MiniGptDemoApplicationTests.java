package com.demo;

import com.demo.service.redis.RedisServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MiniGptDemoApplicationTests {
    @Value("${spring.data.redis.host}")
    private String host;

    @Test
    public void test(){
        System.out.println(host);
    }


}
