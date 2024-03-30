package com.minigptdemo.controller;

import com.minigptdemo.constant.ResultCode;
import com.minigptdemo.pojo.R;
import com.minigptdemo.pojo.dto.RegisterDto;
import com.minigptdemo.service.RegisterService;
import com.minigptdemo.service.redis.RedisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class RegisterController {
    @Resource
    private RegisterService registerService;
    @Resource
    private RedisService redisService;

    @PostMapping("/register")
    public R<String> userRegister(RegisterDto registerDto){
        if(!redisService.isContainsKey(registerDto.getCheckCode())){
            return R.error(ResultCode.VALIDATE_FAILED,"验证码错误");
        }
        String username = registerService.userRegister(registerDto);
        return R.success("用户" + username + "注册成功");
    }


}
