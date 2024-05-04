package com.demo.commentbot.service;

import com.demo.commentbot.pojo.dto.Chat;

import java.util.ArrayList;

public interface GptService {

    //用户发送信息给gpt
    Chat sendMessage(String token, ArrayList<Integer> ids);
}
