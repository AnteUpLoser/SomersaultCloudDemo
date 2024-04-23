package com.demo.service;

/**
 * 响应码枚举类需要使用的业务方法
 */

public interface ResultCodeService {
    /**
     * 返回响应码
     */
    int getCode();

    /**
     * 返回信息
     */
    String getMessage();
}