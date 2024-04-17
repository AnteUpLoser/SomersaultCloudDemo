package com.minigptdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minigptdemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginDao extends BaseMapper<User> {
    @Select("select password from user_info where user_email = #{email};")
    String findPasswordByUserEmail(String email);

}
