package com.minigptdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.minigptdemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao extends BaseMapper<User> {


}
