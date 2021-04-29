package com.example.leetcode.utils.web_crawler;

import com.example.leetcode.common.SymbolCons;
import com.example.leetcode.utils.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: Rxh
 * @Date: 2019/12/27 14:37
 * @Description:
 */
public class BaiduTiebaWebManager extends WebManager {
    private static final String perfix = "tieba.baidu.com";
    private static final String pageParam = "pn=";
    private static final String maxPageRegex = "max-page=\"(.+?)\"";

    private String tid;//帖子id
    /**
     * max-page="54"
     */
    private int maxPage = 1;

    public BaiduTiebaWebManager() {
    }

    /**
     * https://tieba.baidu.com/p/6435170603?see_lz=1
     *
     * @param webUrl
     */
    public BaiduTiebaWebManager(String webUrl) {
        if (webUrl.contains("//tieba.baidu.com/p/")) {
            int startIdx = webUrl.indexOf("p/") + 2;
            int endIdx = webUrl.contains("?") ? webUrl.indexOf("?") : webUrl.length();
            tid = webUrl.substring(startIdx, endIdx);
        }
    }

    /**
     * 分析web网页 获取数据
     *
     * @param html
     */
    @Override
    public void analysisHtml(String html) {
        if (StringUtils.isNotBlank(html)) {
            //提取最大页码
            Pattern pattern = Pattern.compile(maxPageRegex);
            Matcher matcher = pattern.matcher(html);
            if (matcher.find()) {
                String maxPageString = matcher.group(1);
                if (StringUtil.isNumric(maxPageString)) {
                    maxPage = Integer.valueOf(maxPageString);
                }
            }
            System.out.println("maxPage: " + maxPage);
        }
    }

    @Override
    public String getNextPageUrl(String url) {
        String nextPageUrl = null;
        if (StringUtils.isNotBlank(url)
                && url.contains(perfix)
                && url.indexOf(perfix) <= 8) {
            if (url.contains(pageParam)) {
                int pageParamIdx = url.indexOf(pageParam);
                int joinerIdx = url.indexOf(SymbolCons.AND, pageParamIdx);
                String pageNum = url.substring(pageParamIdx + pageParam.length(), joinerIdx < 0 ? url.length() : joinerIdx);

                if (StringUtil.isNumric(pageNum)) {
                    Integer page = Integer.valueOf(pageNum);
                    if (page < maxPage) {
                        nextPageUrl = url.replaceFirst(
                                pageParam + pageNum,
                                pageParam + (page + 1)
                        );
                    }
                }
            } else {
                if (maxPage > 1) {
                    nextPageUrl = url
                            + (url.contains(SymbolCons.QUESTION_MARK) ? SymbolCons.AND : SymbolCons.QUESTION_MARK)
                            + pageParam + 2;
                }
            }
        }
        return nextPageUrl;
    }

    /**
     * @param urls
     * @return
     */
    @Override
    public List<String> getOriginalImageUrls(List<String> urls) {
        List<String> oriUrls = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(urls)) {
            urls.forEach(url -> {
                String oriImageUrl = getOriImageUrl(url);
                if (oriImageUrl != null) oriUrls.add(oriImageUrl);
            });
        }
        return oriUrls;
    }

    /*
        https://tiebapic.baidu.com/forum/pic/item/cb1349540923dd54dcfa9012c609b3de9c82483a.jpg
        http://tieba.baidu.com/photo/p?tid=6435170603&pic_id=ba4686b0cb13495490ebdf1e414e9258d0094a69
        image_original_original
     * webUrl:https://tieba.baidu.com/p/6435170603
     * url=http://tiebapic.baidu.com/forum/w%3D580/sign=29787e939a1001e94e3c1407880f7b06/798ff6fbaf51f3de3e3b8e1e83eef01f3b29795d.jpg
     * tid = 6435170603
     * oriUrl=http://tieba.baidu.com/photo/p?tid=6435170603&pic_id=798ff6fbaf51f3de3e3b8e1e83eef01f3b29795d
     */
    private String getOriImageUrl(String url) {
        if (StringUtils.isNotBlank(url) && StringUtils.isNotEmpty(tid) && url.contains("sign=")) {
            String[] split = url.split("/");
            if (split.length > 0) {
                String pic_id = split[split.length - 1];
                pic_id = pic_id.substring(0, pic_id.indexOf("."));
                return "http://tieba.baidu.com/photo/p?tid=" + tid + "&pic_id=" + pic_id;
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        System.out.println(new BaiduTiebaWebManager().getNextPageUrl("https://tieba.baidu.com/p/6000125949?see_lz=1&pn=3"));
        System.out.println(new BaiduTiebaWebManager(
                "https://tieba.baidu.com/p/6435170603?see_lz=1")
                .getOriImageUrl("http://tiebapic.baidu.com/forum/w%3D580/sign=29787e939a1001e94e3c1407880f7b06" +
                        "/798ff6fbaf51f3de3e3b8e1e83eef01f3b29795d.jpg"));
    }
}
