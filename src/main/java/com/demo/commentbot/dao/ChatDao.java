package com.demo.commentbot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.commentbot.pojo.dto.Chat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatDao extends BaseMapper<Chat> {

}
