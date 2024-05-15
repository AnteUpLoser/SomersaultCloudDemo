package com.demo.commentbot.service;

import com.demo.commentbot.pojo.dto.SendRes;
import com.demo.commentbot.pojo.gpt.FrontReq;

public interface GptService {

    //用户发送信息给gpt
    SendRes sendMessage(String token, FrontReq labelMessage);

    //新建Chat
    Integer newChat(String token);
}
