package com.demo.commentbot.pojo.gpt;

import lombok.Data;


@Data
public class InitialChat {
    private String stuName;
    private String historyComment;
    private Integer bot_id;

    public InitialChat(String stuName, String historyComment, Integer botId){
        this.stuName = stuName;
        this.historyComment = historyComment;
        this.bot_id = botId;
    }

}
