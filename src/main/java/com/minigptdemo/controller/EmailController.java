package com.minigptdemo.controller;


import com.minigptdemo.constant.RedisConstants;
import com.minigptdemo.constant.ResultCode;
import com.minigptdemo.pojo.Email;
import com.minigptdemo.pojo.R;
import com.minigptdemo.service.redis.RedisService;
import com.minigptdemo.utils.email.EmailUtil;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class EmailController {
    @Resource
    private RedisService redisService;

    @PostMapping("/send/register/email")
    public R<String> sendRegisterEmail(@RequestBody Email email){
        EmailUtil emailUtil = new EmailUtil();
        String checkCode = emailUtil.sendRegisterMail(email.getAddress());
        if(checkCode == null) return R.failed(ResultCode.FAILED,"邮件发送失败",null);
        //存入redis
        redisService.setHalfHourValue(RedisConstants.REGISTER_EMAIL_CODE+email.getAddress(), checkCode);
        return R.success(ResultCode.NO_CONTENT_SUCCESS,"邮件发送成功",null);
    }

    @PostMapping("/recheck/register/email")
    public R<String> recheckRegisterEmail(@RequestBody Email email){
        String rightCode = (String) redisService.getValue(RedisConstants.REGISTER_EMAIL_CODE+email.getAddress());
        if(email.getEmailCode().equals(rightCode)){
            return R.success(ResultCode.NO_CONTENT_SUCCESS,"邮件验证码正确",null);
        }
        return R.failed(ResultCode.VALIDATE_FAILED,"邮件验证码错误",null);
    }
}
