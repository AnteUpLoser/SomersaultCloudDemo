package com.demo.commentbot.pojo.dto;

import lombok.Data;

@Data
public class LabelInfoDto {
    //该评价的id
    private Integer id;
    //该类别所有的评价内容
    private String labelText;
}
