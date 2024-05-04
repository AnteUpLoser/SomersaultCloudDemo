package com.demo.commentbot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.commentbot.pojo.LabelInfo;
import com.demo.commentbot.pojo.dto.LabelInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface LabelInfoDao extends BaseMapper<LabelInfo> {

    //查询好评价标签
    @Select("select id,label_text from label_info where label_id = #{labelID} AND is_positive = 1")
    List<LabelInfoDto> getPositiveLabels(int labelID);

    //查询坏评价标签
    @Select("select id,label_text from label_info where label_id = #{labelID} AND is_positive = 0")
    List<LabelInfoDto> getNegativeLabels(int labelID);

    //批量查询集合中id的labelText
    List<String> getLabelInfoList(List<Integer> ids);

}
