package com.demo.pojo;

import lombok.Data;

@Data
public class CheckCode {
    private int width;
    private int height;
    private int verifySize;
    private String sessionID;
}
