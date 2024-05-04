package com.demo.commentbot.pojo.gpt;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class GptChoices {
    @JSONField(name = "index")
    private int index;
    @JSONField(name = "message")
    private Message resMessage;

}
