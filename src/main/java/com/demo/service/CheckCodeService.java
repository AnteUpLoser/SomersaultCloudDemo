package com.demo.service;

import com.demo.pojo.CheckCode;
import jakarta.servlet.http.HttpServletResponse;


public interface CheckCodeService  {
    //获取验证码图片
    String getCheckCodeImg(HttpServletResponse response, CheckCode checkCode);

    //校验验证码是否正确
    String recheckCodeIsTrue(String sessionID, String checkCode);
}
