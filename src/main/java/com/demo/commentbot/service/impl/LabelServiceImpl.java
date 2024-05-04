package com.demo.commentbot.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.demo.commentbot.dao.LabelDao;
import com.demo.commentbot.dao.LabelInfoDao;
import com.demo.commentbot.pojo.Label;
import com.demo.commentbot.pojo.dto.LabelInfoDto;
import com.demo.commentbot.service.LabelService;
import com.demo.constant.RedisConstants;
import com.demo.service.redis.RedisService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO 存入redis操作

@Service
public class LabelServiceImpl implements LabelService {
    @Resource
    private LabelDao labelDao;
    @Resource
    private LabelInfoDao labelInfoDao;
    @Resource
    private RedisService redisService;


    public List<Label> getAllLabel() {
        String redisKey = RedisConstants.COMMENT_BOT_LABEL_KINDS;
        List<Label> res;
        //先查询redis缓存 再查询数据库
        if(redisService.isContainsKey(redisKey)){
            TypeReference<List<Label>> typeReference = new TypeReference<>() {};
            res = JSON.parseObject(redisService.getValue(redisKey),typeReference);
            return res;
        }else{
            res = labelDao.selectList(null);
            String redisValue = JSON.toJSONString(res);
            redisService.setHalfHourValue(redisKey,redisValue);
        }

        return res;
    }


    public List<LabelInfoDto> getPositiveLabels(int labelID) {
        String redisKey = RedisConstants.COMMENT_BOT_LABEL_POSITIVE+labelID;
        List<LabelInfoDto> res;
        //先查询redis缓存 再查询数据库
        if(redisService.isContainsKey(redisKey)){
            TypeReference<List<LabelInfoDto>> typeReference = new TypeReference<>() {};
            res = JSON.parseObject(redisService.getValue(redisKey),typeReference);
        }else {
            res = labelInfoDao.getPositiveLabels(labelID);
            String redisValue = JSON.toJSONString(res);
            redisService.setHalfHourValue(redisKey,redisValue);
        }

        return res;
    }


    public List<LabelInfoDto> getNegativeLabels(int labelID) {
        String redisKey = RedisConstants.COMMENT_BOT_LABEL_NEGATIVE+labelID;
        List<LabelInfoDto> res;
        //先查询redis缓存 再查询数据库
        if(redisService.isContainsKey(redisKey)){
            TypeReference<List<LabelInfoDto>> typeReference = new TypeReference<>() {};
            res = JSON.parseObject(redisService.getValue(redisKey),typeReference);
        }else {
            res = labelInfoDao.getNegativeLabels(labelID);
            String redisValue = JSON.toJSONString(res);
            redisService.setHalfHourValue(redisKey,redisValue);
        }

        return res;
    }


}
