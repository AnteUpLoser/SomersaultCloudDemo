package com.minigptdemo.controller;


import com.minigptdemo.constant.ResultCode;
import com.minigptdemo.dao.LoginDao;
import com.minigptdemo.pojo.R;
import com.minigptdemo.pojo.dto.LoginDto;
import com.minigptdemo.service.LoginService;
import com.minigptdemo.utils.encryption.EncryptUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {
    @Resource
    private LoginDao loginDao;
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public R<String> userLogin(@RequestBody LoginDto loginDto){
        String SHAPassword = loginDao.findPasswordByUserEmail(loginDto.getUserEmail());
        if(SHAPassword == null){
            return R.error(ResultCode.VALIDATE_FAILED,"邮箱不存在");
        }
        if(!EncryptUtil.verify(loginDto.getPassword(),SHAPassword)){
            return R.error(ResultCode.VALIDATE_FAILED,"密码错误");
        }
        String token = loginService.userLogin(loginDto);
        return R.success(ResultCode.SUCCESS,"登录成功",token);

    }

}
