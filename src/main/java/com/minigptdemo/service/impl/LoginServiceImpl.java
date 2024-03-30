package com.minigptdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minigptdemo.dao.LoginDao;
import com.minigptdemo.pojo.User;
import com.minigptdemo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class LoginServiceImpl extends ServiceImpl<LoginDao, User> implements LoginService {


    public User userLogin(User user) {
        return user;
    }
}
