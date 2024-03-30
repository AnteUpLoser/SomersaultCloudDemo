package com.minigptdemo.service;

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