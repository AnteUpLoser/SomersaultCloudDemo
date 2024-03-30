package com.minigptdemo.controller;


import com.minigptdemo.pojo.R;
import com.minigptdemo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {

    @PostMapping("/login")
    public R<User> userLogin(User user){
        return null;
    }

}
