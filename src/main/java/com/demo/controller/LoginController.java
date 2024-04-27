package com.demo.controller;


import com.demo.constant.ResultCode;
import com.demo.dao.LoginDao;
import com.demo.pojo.R;
import com.demo.pojo.dto.LoginDto;
import com.demo.service.LoginService;
import com.demo.utils.encryption.EncryptUtil;
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
        String realPwd = loginDao.findPasswordByUserEmail(loginDto.getUserEmail());
        if(realPwd == null){
            return R.error(ResultCode.VALIDATE_FAILED,"用户不存在");
        }
        if(!EncryptUtil .verifyUserPwd(loginDto.getPassword(),loginDto.getUserEmail(),realPwd)){
            return R.error(ResultCode.VALIDATE_FAILED,"密码错误");
        }

        String token = loginService.userLogin(loginDto);
        return R.success(ResultCode.SUCCESS,"登录成功",token);

    }

}
