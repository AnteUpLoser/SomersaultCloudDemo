package com.minigptdemo.service.redis;




public interface RedisService {

    //获取对应key的value值
    String getValue(String key);

    //设置持久化kv
    void setValue(String key, String value);

    //删除kv
    void deleteValue(String key);

    //判断是否存在kv
    boolean isContainsKey(String key);

    //设置一分钟有效期的值
    void setOneMinValue(String key, String value);

    //设置多少分钟有效值
    void setValueByMin(String key, String value,int min);

    //设置半小时有效期的值
    void setHalfHourValue(String key, String value);
}
