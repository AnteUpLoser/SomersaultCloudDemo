package com.minigptdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.minigptdemo.pojo.User;
import com.minigptdemo.pojo.dto.RegisterDto;


public interface RegisterService extends IService<RegisterDto> {
    /**
     * 用户注册
     */
    String userRegister(RegisterDto registerDto);

}
