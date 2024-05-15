package com.demo.commentbot.pojo.gpt;


import com.demo.commentbot.pojo.Bot;
import com.demo.commentbot.pojo.BotSysPrompt;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GptReq {
    private String model;
    private ArrayList<Message> messages;

    public GptReq(String userPrompt){
        this.model = Bot.COMMENT_BOT;
        Message systemMessage = new Message().setSysMessage(BotSysPrompt.COMMENT_BOT_PROMPT);
        Message userMessage = new Message().setUserMessage(userPrompt);
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(systemMessage);
        messages.add(userMessage);
        this.messages = messages;
    }
}
