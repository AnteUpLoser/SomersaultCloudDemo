package com.minigptdemo.pojo.dto;
import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    //邮箱或电话号码？
    private String userEmail;

}
