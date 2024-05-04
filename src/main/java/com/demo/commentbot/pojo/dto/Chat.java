package com.demo.commentbot.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Chat {
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "role")
    private String role;
    @JSONField(name = "message")
    private String message;

}
