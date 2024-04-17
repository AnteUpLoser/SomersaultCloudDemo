package com.minigptdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.minigptdemo.constant.RedisConstants;
import com.minigptdemo.dao.LoginDao;
import com.minigptdemo.pojo.User;
import com.minigptdemo.pojo.dto.LoginDto;
import com.minigptdemo.service.LoginService;
import com.minigptdemo.service.redis.RedisService;
import com.minigptdemo.utils.jwt.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class LoginServiceImpl extends ServiceImpl<LoginDao, User> implements LoginService {
    @Resource
    private RedisService redisService;

    public String userLogin(LoginDto user) {
        //已登录情况直接返回缓存token
        if(redisService.isContainsKey(RedisConstants.USER_TOKEN+user.getUserEmail())){
            return (String) redisService.getValue(RedisConstants.USER_TOKEN+user.getUserEmail());
        }

        Map<String, Object> tokenMap = new HashMap<>();
        //token携带用户邮箱
        tokenMap.put("userEmail",user.getUserEmail());
        String token = JwtUtil.createJwt(tokenMap);

        //TODO 待改：用户token时长
        //将登录用户token存入缓存
        redisService.setHalfHourValue(RedisConstants.USER_TOKEN+user.getUserEmail(),token);

        return token;
    }
}
