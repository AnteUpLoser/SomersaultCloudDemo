package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.RegisterDao;
import com.demo.pojo.dto.LoginDto;
import com.demo.pojo.dto.RegisterDto;
import com.demo.service.LoginService;
import com.demo.service.RegisterService;
import com.demo.utils.encryption.EncryptUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterDao, RegisterDto> implements RegisterService {

    @Resource
    private RegisterDao registerDao;
    @Resource
    private LoginService loginService;


    /**
     * 用户注册实现
     * @return jwt
     */
    public String userRegister(RegisterDto registerDto){
        String password = EncryptUtil.SHAForRegister(registerDto.getPassword(), registerDto.getUserEmail());

        registerDto.setPassword(password);
        registerDao.insert(registerDto);

        String userEmail = registerDto.getUserEmail();
        String userPwd = registerDto.getPassword();
        LoginDto loginUser = new LoginDto();
        loginUser.setUserEmail(userEmail);
        loginUser.setPassword(userPwd);

        //返回JWT令牌
        return loginService.userLogin(loginUser);
    }


}
