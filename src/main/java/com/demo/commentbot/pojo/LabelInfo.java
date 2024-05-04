package com.demo.commentbot.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("label_info")
public class LabelInfo {
    //该评价的id
    private Integer id;
    //该类别所有的评价内容
    private String labelText;
    //该评价的好坏
    private int isPositive;
}
