package com.papergpt.pojo;


import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class Message {
    private String role;
    private String content;

    @JSONField(name = "role")
    public String getRole() {
        return role;
    }

    @JSONField(name = "content")
    public String getContent() {
        return content;
    }
}
