package com.minigptdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minigptdemo.pojo.dto.RegisterDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterDao extends BaseMapper<RegisterDto> {

    //注册新增用户
    @Insert("insert into user_info (username,password,user_email) values(#{username},#{password},#{userEmail})")
    void insertNewUser(RegisterDto registerDto);
}
