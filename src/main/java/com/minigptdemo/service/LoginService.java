package com.minigptdemo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.minigptdemo.pojo.User;
import com.minigptdemo.pojo.dto.LoginDto;


public interface LoginService extends IService<User> {
    /**
     * 普通用户登录
     */
    String userLogin(LoginDto user);

}
