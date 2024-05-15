package com.demo.commentbot.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserChat {
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "role")
    private String role;
    @JSONField(name = "message")
    private String message;
    @JSONField(name = "labelIDs")
    private ArrayList<Integer> labelIDs;


}
