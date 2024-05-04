package com.demo.commentbot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "label")
public class Label {
    private Integer id;
    private String labelName;
}
