package com.demo.controller;

import com.demo.constant.ResultCode;
import com.demo.dao.RegisterDao;
import com.demo.pojo.R;
import com.demo.pojo.dto.RegisterDto;
import com.demo.service.LoginService;
import com.demo.service.RegisterService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        String token = registerService.userRegister(registerDto);
        String username = registerDto.getUsername();
        return R.success(ResultCode.SUCCESS,"用户" + username + "注册成功",token);
    }


}
