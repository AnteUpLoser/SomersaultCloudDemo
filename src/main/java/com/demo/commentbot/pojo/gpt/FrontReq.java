package com.demo.commentbot.pojo.gpt;

import lombok.Data;

import java.util.ArrayList;

@Data
public class FrontReq {
    private String stuName;
    private String historyComment;
    private ArrayList<Integer> labelIds;

    public FrontReq(String stuName, String historyComment, ArrayList<Integer> labelIds){
        this.stuName = stuName;
        this.historyComment = historyComment;
        this.labelIds = labelIds;
    }

}
