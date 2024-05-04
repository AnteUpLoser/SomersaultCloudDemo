package com.demo.commentbot.service;


import com.demo.commentbot.pojo.Label;
import com.demo.commentbot.pojo.dto.LabelInfoDto;

import java.util.List;

public interface LabelService {
    //查询所有Label类别信息并返回
    List<Label> getAllLabel();

    //查询该类别的所有积极评价标签
    List<LabelInfoDto> getPositiveLabels(int labelID);

    //查询该类别所有的消极评价标签
    List<LabelInfoDto> getNegativeLabels(int labelID);
}
