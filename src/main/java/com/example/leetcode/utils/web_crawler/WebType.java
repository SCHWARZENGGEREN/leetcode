package com.example.leetcode.utils.web_crawler;

public enum WebType {
    NONE(1),
    BAIDU_TIEBA(1),
    MIHOYO_BBS(2),
    ZHIHU(3),
    ;
    public int type;

    WebType(int type) {
        this.type = type;
    }
}