package com.demo.service;

import com.demo.pojo.Email;


public interface EmailService {
    //注册邮箱验证码的发送
    String sendRegisterMail(String mailAddress);

    //校验注册邮箱的验证码
    boolean checkRegisterMail(Email email);
}
