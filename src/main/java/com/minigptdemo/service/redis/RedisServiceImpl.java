package com.minigptdemo.service.redis;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 *
 *  封装stringRedisTemplate底层方法
 * 易于自己使用
 */

@Slf4j
@Service
public class RedisServiceImpl implements RedisService{
    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    //获取对应key的value值
    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    //设置kv
    public void setValue(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    //删除kv
    public void deleteValue(String key){
        redisTemplate.delete(key);
    }

    //判断是否存在kv
    public boolean isContainsKey(String key){
        Boolean result = redisTemplate.hasKey(key);
        return result != null && result;
    }

    //设置一分钟有效期的值
    public void setOneMinValue(String key, String value){
        redisTemplate.opsForValue().set(key,value,1, TimeUnit.MINUTES);
    }

    //设置半小时有效期的值
    public void setHalfHourValue(String key, String value) {
        redisTemplate.opsForValue().set(key,value,30,TimeUnit.MINUTES);
    }


}
