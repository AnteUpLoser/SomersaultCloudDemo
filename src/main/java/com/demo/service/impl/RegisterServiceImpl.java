package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.RegisterDao;
import com.demo.pojo.dto.RegisterDto;
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


    /**
     * 用户注册实现
     */
    public String userRegister(RegisterDto registerDto){
        String password = EncryptUtil.SHA(registerDto.getPassword());

        System.out.println(password);
        registerDto.setPassword(password);
        registerDao.insertNewUser(registerDto);

        return registerDto.getUsername();
    }


}
