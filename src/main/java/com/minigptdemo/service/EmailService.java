package com.minigptdemo.service;

import com.minigptdemo.pojo.Email;


public interface EmailService {
    //注册邮箱验证码的发送
    String sendRegisterMail(String mailAddress);

    //校验注册邮箱的验证码
    boolean checkRegisterMail(Email email);
}
