package com.demo.commentbot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.commentbot.pojo.LabelInfo;
import com.demo.commentbot.pojo.dto.LabelInfoDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface LabelInfoDao extends BaseMapper<LabelInfo> {

    //查询好坏标签
    List<LabelInfoDto> getLabels(int labelId, int isPositive);

    //批量查询集合中id的labelText
    List<String> getLabelInfoList(List<Integer> ids);

}
