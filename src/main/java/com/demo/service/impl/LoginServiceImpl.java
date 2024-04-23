package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.constant.RedisConstants;
import com.demo.dao.LoginDao;
import com.demo.pojo.dto.LoginDto;
import com.demo.service.LoginService;
import com.demo.service.redis.RedisService;
import com.demo.utils.jwt.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class LoginServiceImpl extends ServiceImpl<LoginDao, LoginDto> implements LoginService {
    @Resource
    private RedisService redisService;
    @Resource
    private LoginDao loginDao;

    public String userLogin(LoginDto user) {
        //已登录情况直接返回缓存token
        if(redisService.isContainsKey(RedisConstants.USER_TOKEN+user.getUserEmail())){
            return redisService.getValue(RedisConstants.USER_TOKEN+user.getUserEmail());
        }

        Map<String, Object> tokenMap = new HashMap<>();
        //token携带用户id
        Integer uid = loginDao.findUidByEmail(user.getUserEmail());
        tokenMap.put("uid",uid);
        String token = JwtUtil.createJwt(tokenMap);

        //TODO 待改：用户token时长
        //将登录用户token存入缓存
        redisService.setHalfHourValue(RedisConstants.USER_TOKEN+user.getUserEmail(),token);

        return token;
    }
}
