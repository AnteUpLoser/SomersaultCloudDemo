package com.minigptdemo.service.impl;

import com.minigptdemo.constant.RedisConstants;
import com.minigptdemo.pojo.CheckCode;
import com.minigptdemo.service.CheckCodeService;
import com.minigptdemo.service.redis.RedisService;
import com.minigptdemo.utils.checkcode.CheckCodeUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CheckCodeServiceImpl  implements CheckCodeService {

    @Resource
    private RedisService redisService;

    public String getCheckCodeImg(HttpServletResponse response, CheckCode checkCode) {
        //客户端发的sessionID
        String sessionID = checkCode.getSessionID();
        ServletOutputStream os = null;
        String checkCodeImg = null;
        try {
            os = response.getOutputStream();
            checkCodeImg = CheckCodeUtil.outputVerifyImage(checkCode.getWidth(), checkCode.getHeight(), os, checkCode.getVerifySize());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置了验证码有效期为一分钟
        redisService.setOneMinValue(RedisConstants.REGISTER_CHECK_CODE+sessionID,checkCodeImg);
        return checkCodeImg;
    }

    public String recheckCodeIsTrue(String sessionID, String checkCode) {
        //获取正确的验证码
        String theRightCode = (String) redisService.getValue(RedisConstants.REGISTER_CHECK_CODE+sessionID);
        if(theRightCode == null) {
            return null;
        }
        if(checkCode.equalsIgnoreCase(theRightCode)){
            return "true";
        }
        return "false";
    }
}
