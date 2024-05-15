package com.demo.commentbot.service;


import com.demo.commentbot.pojo.Label;
import com.demo.commentbot.pojo.dto.LabelInfoDto;

import java.util.List;

public interface LabelService {
    //查询所有Label类别信息并返回
    List<Label> getAllLabel();

    //查询该类别的好坏标签
    List<LabelInfoDto> getLabels(int labelId, int isPositive);
}
