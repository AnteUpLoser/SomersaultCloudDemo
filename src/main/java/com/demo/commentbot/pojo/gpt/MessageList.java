package com.demo.commentbot.pojo.gpt;


import lombok.Data;

import java.util.ArrayList;

// 消息队列的数据结构
@Data
public class MessageList {
    private ArrayList<Message> messageList;
}
