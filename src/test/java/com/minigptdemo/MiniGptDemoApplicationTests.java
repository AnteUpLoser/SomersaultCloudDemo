package com.minigptdemo;

import com.minigptdemo.utils.encryption.EncryptUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MiniGptDemoApplicationTests {

    @Test
    public static void main(String[] args) {
        String password = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
        System.out.println(EncryptUtil.verify("123456", password));
    }

}
