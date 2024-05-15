package com.demo.commentbot.controller;

import com.alibaba.fastjson.JSON;
import com.demo.commentbot.pojo.*;
import com.demo.commentbot.pojo.dto.UserChat;
import com.demo.commentbot.pojo.dto.LabelInfoDto;
import com.demo.commentbot.pojo.dto.SendRes;
import com.demo.commentbot.pojo.gpt.FrontReq;
import com.demo.commentbot.service.LabelService;
import com.demo.constant.RedisConstants;
import com.demo.constant.ResultCode;
import com.demo.pojo.R;
import com.demo.commentbot.service.GptService;
import com.demo.service.redis.RedisService;
import com.demo.utils.jwt.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/commentBot")
public class GptController {
    @Resource
    private GptService gptService;
    @Resource
    private LabelService labelService;
    @Resource
    private RedisService redisService;

    @PostMapping("/init")
    public R<Integer> initCommentBotChat(@RequestHeader String token){
        int chatId = gptService.newChat(token);
        return R.success(chatId);
    }

    @PostMapping("/send")
    public R<Object> sendMessage(@RequestHeader String token,
                                 @RequestBody FrontReq frontReq){
        // 发送请求并获取响应
        SendRes resDto = gptService.sendMessage(token,frontReq);
        System.out.println(frontReq);
        // 处理响应...
        if(resDto == null) return R.error(ResultCode.FAILED,"出现错误响应");
        if(resDto.getId() == null) return R.failed(ResultCode.UNAUTHORIZED,"无效token",token);

        return R.success(resDto,"响应成功");
    }

    @GetMapping("/get/labels")
    public R<List<Label>> getAllLabel(){
        List<Label> res = labelService.getAllLabel();
        return R.success(res);
    }

    @GetMapping("/get/{labelId}/labels")
    public R<List<LabelInfoDto>> getPositiveLabels(@PathVariable int labelId,
                                                   @RequestParam int isPositive){
        List<LabelInfoDto> res = labelService.getLabels(labelId, isPositive);
        return R.success(res);
    }



    //获取聊天记录
    @GetMapping("/get/conversation/history")
    public R<Object> getConversationHistory(@RequestHeader String token){
        Integer uid = JwtUtil.getUidByJwt(token);
        List<UserChat> res = JSON.parseArray(redisService.getChatMessages(RedisConstants.COMMENTBOT_CONVERSATION_HISTORY_USER+uid).toString(), UserChat.class);
        return R.success(res);
    }
}
