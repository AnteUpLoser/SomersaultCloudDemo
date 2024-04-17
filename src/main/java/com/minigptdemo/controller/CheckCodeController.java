package com.minigptdemo.controller;

import com.minigptdemo.constant.ResultCode;
import com.minigptdemo.pojo.CheckCode;
import com.minigptdemo.pojo.R;
import com.minigptdemo.service.CheckCodeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 图片验证码
 */
@Slf4j
@RestController
public class CheckCodeController {
    @Resource
    private CheckCodeService checkCodeService;

    //获取验证码及图片
    @PostMapping("/checkCodeImage")
    public String checkCode(HttpServletResponse response, @RequestBody CheckCode checkCode){
        return checkCodeService.getCheckCodeImg(response, checkCode);
    }


    //校验图片验证码
    @PostMapping("/recheck/checkCode")
    public R<String> recheckCode(@RequestHeader String sessionID, @RequestBody String checkCode){
        String res = checkCodeService.recheckCodeIsTrue(sessionID, checkCode);
        //过期
        if(res == null) return R.error(ResultCode.VALIDATE_FAILED,"验证码已过期");

        if("true".equals(res)){
            return R.success(ResultCode.NO_CONTENT_SUCCESS,"验证码正确",res);
        }
        return R.failed(ResultCode.VALIDATE_FAILED,"验证码错误",res);
    }

}
