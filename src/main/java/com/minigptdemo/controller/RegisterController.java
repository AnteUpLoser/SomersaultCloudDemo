package com.minigptdemo.controller;

import com.minigptdemo.constant.ResultCode;
import com.minigptdemo.dao.RegisterDao;
import com.minigptdemo.pojo.R;
import com.minigptdemo.pojo.dto.RegisterDto;
import com.minigptdemo.service.RegisterService;
import com.minigptdemo.service.redis.RedisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class RegisterController {
    @Resource
    private RegisterService registerService;
    @Resource
    private RegisterDao registerDao;


    @PostMapping("/register")
    public R<String> userRegister(@RequestBody RegisterDto registerDto){
        if(registerDao.selectSameUsername(registerDto.getUsername())){
            return R.error(ResultCode.VALIDATE_FAILED,"用户名已存在");
        }else if(registerDao.selectSameEmail(registerDto.getUserEmail())){
            return R.error(ResultCode.VALIDATE_FAILED,"邮箱已注册");
        }

        String username = registerService.userRegister(registerDto);
        return R.success("用户" + username + "注册成功");
    }


}
