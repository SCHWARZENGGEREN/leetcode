package com.example.leetcode.utils.web_crawler;

public class WebUtil {

    public static final String BAIDU_TIEBA_PERFIX = "https://tieba.baidu.com/";
    public static final String MIHOYO_BBS_PERFIX = "";
    public static final String ZHIHU_PERFIX = "";

    public static boolean validImageUrl(String url) {
        return url != null
                && url.length() < 250
                && url.startsWith("http")
                && (url.endsWith("jpg") || url.endsWith("png"));
    }
}