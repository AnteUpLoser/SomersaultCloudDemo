package com.demo;

import com.alibaba.fastjson.JSON;
import com.demo.commentbot.dao.LabelInfoDao;
import com.demo.commentbot.pojo.dto.UserChat;
import com.demo.commentbot.pojo.gpt.GptReq;
import com.demo.commentbot.pojo.gpt.Message;
import com.demo.commentbot.pojo.Bot;
import com.demo.pojo.dto.RegisterDto;
import com.demo.service.redis.RedisService;
import com.demo.utils.encryption.EncryptUtil;
import com.demo.utils.jwt.JwtUtil;
import jakarta.annotation.Resource;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@SpringBootApplication
class MiniGptDemoApplicationTests {
    @Resource
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
        UserChat chat = new UserChat();
        chat.setId("3");
        chat.setMessage("hello");
        String jsonChat = JSON.toJSONString(chat);

        redisService.storeChatMessage("chat",jsonChat, System.currentTimeMillis());
        System.out.println(redisService.getChatMessages("chat"));
    }

    @Test
    public void sendTest(){
        String req = "年后";
        System.out.println(sendMsg(req));
    }

    //请求官方api的函数
    public String sendMsg(String reqString) {
        // 创建代理服务器的主机和端口
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Bot.PROXY_HOSTNAME, Bot.PROXY_PORT));
        // 创建 OkHttpClient.Builder 实例，并配置代理
        OkHttpClient.Builder builder = new OkHttpClient.Builder().proxy(proxy);
        // 创建 OkHttpClient 实例
        OkHttpClient client = builder.build();

        //创建请求体
        MediaType mediaType = MediaType.parse("application/json");
        //拼好请求的文本
        GptReq gptReq = new GptReq(reqString);
        String req = JSON.toJSONString(gptReq);
        RequestBody requestBody = RequestBody.create(req, mediaType);
        // 创建 POST 请求
        Request request = new Request.Builder()
                .url(Bot.API_URL)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + Bot.SK)
                .post(requestBody)
                .build();
        // 发送请求并获取响应
        try {
            Response response = client.newCall(request).execute();
            //解决不可能的空指针
            String responseBody = Objects.requireNonNull(response.body()).string();

            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void jwtTest(){
        System.out.println(JwtUtil.getUidByJwt("eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOi0xfQ.LlHkLQ_aO5TYvJT9PXegUmPnOaJDLsDU2kshO3cSxq0"));
    }
}
