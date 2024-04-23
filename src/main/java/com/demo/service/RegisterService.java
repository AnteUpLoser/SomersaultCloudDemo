package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.dto.RegisterDto;


public interface RegisterService extends IService<RegisterDto> {
    /**
     * 用户注册
     */
    String userRegister(RegisterDto registerDto);

}
