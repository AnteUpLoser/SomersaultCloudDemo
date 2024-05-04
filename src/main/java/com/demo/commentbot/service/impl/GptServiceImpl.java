package com.demo.commentbot.service.impl;


import com.alibaba.fastjson.JSON;
import com.demo.commentbot.dao.LabelInfoDao;
import com.demo.commentbot.pojo.dto.Chat;
import com.demo.commentbot.pojo.gpt.GptReq;
import com.demo.commentbot.pojo.gpt.GptRes;
import com.demo.commentbot.service.GptService;
import com.demo.constant.Bot;
import com.demo.constant.RedisConstants;
import com.demo.service.redis.RedisService;
import com.demo.utils.jwt.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class GptServiceImpl implements GptService {
    @Resource
    private LabelInfoDao labelInfoDao;
    @Resource
    private RedisService redisService;

    public Chat sendMessage(String token, ArrayList<Integer> ids) {
        Chat res = new Chat();
        //TODO 对用户进行token统计
        //检验token
        Integer uid = JwtUtil.getUidByJwt(token);
        System.out.println(uid);
        if(uid == null) return res;

        // 创建代理服务器的主机和端口
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Bot.PROXY_HOSTNAME, Bot.PROXY_PORT));
        // 创建 OkHttpClient.Builder 实例，并配置代理
        OkHttpClient.Builder builder = new OkHttpClient.Builder().proxy(proxy);
        // 创建 OkHttpClient 实例
        OkHttpClient client = builder.build();

        //创建请求体
        MediaType mediaType = MediaType.parse("application/json");
        //得到所有标签拼成的评价语
        String labels = String.valueOf(labelInfoDao.getLabelInfoList(ids));
        GptReq gptReq = new GptReq(labels);
        String req = JSON.toJSONString(gptReq);
        RequestBody requestBody = RequestBody.create(req,mediaType);
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
            // 处理响应
            int statusCode = response.code();
            //不正确的响应时返回异常null
            if(statusCode != 200) return null;

            //解决不可能的空指针
            String responseBody = Objects.requireNonNull(response.body()).string();
            GptRes gptRes = JSON.parseObject(responseBody,GptRes.class);

            //请求chat
            Chat chatReq = new Chat();
            chatReq.setId(String.valueOf(UUID.randomUUID()));
            chatReq.setRole("user");
            chatReq.setMessage(labels);


            //响应chat
            res.setMessage(gptRes.getChoices().get(0).getResMessage().getContent());
            res.setRole("assistant");
            res.setId(gptRes.getId());

            //存入redis聊天记录
            String reqRedisValue = JSON.toJSONString(chatReq);
            String resRedisValue = JSON.toJSONString(res);
            redisService.storeChatMessage(RedisConstants.COMMENTBOT_CONVERSATION_HISTORY_USER+uid,reqRedisValue,System.currentTimeMillis());
            redisService.storeChatMessage(RedisConstants.COMMENTBOT_CONVERSATION_HISTORY_USER+uid,resRedisValue,System.currentTimeMillis());
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

}
