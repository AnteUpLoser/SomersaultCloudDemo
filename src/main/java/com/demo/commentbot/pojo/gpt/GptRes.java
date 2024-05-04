package com.demo.commentbot.pojo.gpt;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GptRes {
    @JSONField(name = "id")
    private String id;      //此次聊天完成的唯一标识
    @JSONField(name = "model")
    private String model;
    @JSONField(name = "choices")
    private ArrayList<GptChoices> choices;


}
