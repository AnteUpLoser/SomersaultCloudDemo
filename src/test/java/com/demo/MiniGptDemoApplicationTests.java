package com.demo;

import com.alibaba.fastjson.JSON;
import com.demo.commentbot.dao.LabelInfoDao;
import com.demo.commentbot.pojo.dto.Chat;
import com.demo.commentbot.pojo.gpt.Message;
import com.demo.dao.RegisterDao;
import com.demo.pojo.dto.RegisterDto;
import com.demo.service.redis.RedisService;
import com.demo.utils.encryption.EncryptUtil;
import com.demo.utils.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class MiniGptDemoApplicationTests {
    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private LabelInfoDao labelInfoDao;
    @Autowired
    private RedisService redisService;


    @Test
    public void httpClientTest(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("uid",123);
        String token = JwtUtil.createJwt(claims);
        System.out.println(token);


    }

    @Test
    public void encryptTest(){
        String password = "Aa130069711..";
        String encodePass = EncryptUtil.SHAForRegister(password,"1750859115@qq.com");

        System.out.println(password);
        System.out.println(encodePass);

        System.out.println(EncryptUtil.verifyUserPwd(password, "1750859115@qq.com",encodePass));
    }

    @Test
    public void mpTest(){
        RegisterDto dto = new RegisterDto();
        dto.setPassword("123");
        dto.setUserEmail("1750859115@qq.com");
    }

    @Test
    public void sysMessage(){
        Message message = new Message();
        message.setSysMessage("你是一个聪明的机器人");
        System.out.println(message);
    }

    //批量查询test
    @Test
    public void selectTest(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(5);
        System.out.println(labelInfoDao.getLabelInfoList(list));
    }

    //聊天记录测试
    @Test
    public void storeTest(){
        Chat chat = new Chat();
        chat.setId("3");
        chat.setMessage("hello");
        String jsonChat = JSON.toJSONString(chat);

        redisService.storeChatMessage("chat",jsonChat, System.currentTimeMillis());
        System.out.println(redisService.getChatMessages("chat"));
    }


}
