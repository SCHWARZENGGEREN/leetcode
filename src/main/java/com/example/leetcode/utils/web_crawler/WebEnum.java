package com.example.leetcode.utils.web_crawler;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Supplier;

public enum WebEnum {
    BAIDU_TIEBA(
            WebType.BAIDU_TIEBA.type
            , "tieba.baidu.com"
            , BaiduTiebaWebManager::new
    ),
    MIHOYO_BBS(//https://bbs.mihoyo.com/bh3/article/449786
            WebType.MIHOYO_BBS.type
            , "bbs.mihoyo.com"
            , () -> null
    ),
    ZHIHU(//https://www.zhihu.com/question/21530813/answer/936928839
            WebType.ZHIHU.type
            , "www.zhihu.com"
            , () -> null
    ),
    DEFAULT(
            WebType.NONE.type
            , ""
            , () -> null
    ),;

    private int type;
    private String urlPerfix;
    private Supplier<? extends WebManager> managerSupplier;

    public Supplier<? extends WebManager> getManagerSupplier() {
        return managerSupplier;
    }

    WebEnum(int type, String urlPerfix, Supplier<? extends WebManager> managerSupplier) {
        this.type = type;
        this.urlPerfix = urlPerfix;
        this.managerSupplier = managerSupplier;
    }

    public static WebEnum getWebEnumByUrl(String url) {
        if (StringUtils.isNotBlank(url)) {
            for (WebEnum webEnum : values()) {
                if (url.contains(webEnum.urlPerfix)) {
                    return webEnum;
                }
            }
        }
        return DEFAULT;
    }
}