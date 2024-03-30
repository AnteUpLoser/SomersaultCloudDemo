package com.minigptdemo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.minigptdemo.pojo.User;



public interface LoginService extends IService<User> {
    /**
     * 普通用户登录
     */
    User userLogin(User user);

}
