package com.minigptdemo.pojo.dto;


import lombok.Data;


//暂时由邮箱和密码登录、
@Data
public class LoginDto {
    private String userEmail;
    private String password;
}
