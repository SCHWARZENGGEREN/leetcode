package com.example.leetcode.utils.web_crawler;

import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: Rxh
 * @Date: 2020/1/9 10:28
 * @Description:
 */
public class WebMannagerFactory {

    public static final String BAIDU_TIEBA_URL = "tieba.baidu.com";
    public static final String MIHOYO_BBS_URL = "bbs.mihoyo.com";
    public static final String ZHIHU_URL = "www.zhihu.com";

    public static WebManager getWebManager(String webUrl){
        if (StringUtils.isNotBlank(webUrl)){
            if (webUrl.contains(BAIDU_TIEBA_URL)){
                return new BaiduTiebaWebManager(webUrl);
            }else if (webUrl.contains(MIHOYO_BBS_URL)){
                return null;
            }else if (webUrl.contains(ZHIHU_URL)){
                return new ZhihuWebManager();
            }
        }
        return null;
    }
}
