package com.example.leetcode.utils.web_crawler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2019/12/27 14:36
 * @Description:
 */
public abstract class WebManager {
    /**
     * 分页
     * @param url
     * @return
     */
    public abstract String getNextPageUrl(String url);

    /**
     * 分析第一页html,获取maxpage等数据
     * @param html
     */
    public abstract void analysisHtml(String html);

    /**
     * 根据图片url获取原图url
     * @param urls
     * @return
     */
    public List<String> getOriginalImageUrls(List<String> urls){
        return new ArrayList<>();
    }
}
