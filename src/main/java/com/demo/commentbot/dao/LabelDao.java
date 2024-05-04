package com.demo.commentbot.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.commentbot.pojo.Label;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LabelDao extends BaseMapper<Label> {

}
