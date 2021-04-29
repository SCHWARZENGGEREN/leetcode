package com.example.leetcode.utils.web_crawler;

import com.example.leetcode.common.SymbolCons;
import com.example.leetcode.utils.StringUtil;
import com.example.leetcode.utils.Util;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: Rxh
 * @Date: 2019/12/25 16:18
 * @Description: 爬虫  爬图片
 */
public class WebCrawler {

    private static final String SAVE_PATH = "E:\\MyDownloads\\pictures\\";
    private static final String IMAGE_PATTERN = "src=\"(.+?)\"";
    private static final String DEFAULT_FORMAT = ".jpg";
    private static final String IMAGE_FORMATS = ",BMP,JPG,JPEG,GIF,PNG,WEBP,bmp,jpg,jpeg,gif,png,webp,";

    public static void main(String[] args) throws Exception {
        //定义即将访问的链接
        String url = "https://tieba.baidu.com/p/6435170603?see_lz=1";
        url = "https://www.zhihu.com/question/23524899/answer/377155367";
        url = "https://tieba.baidu.com/p/5652915506?see_lz=1";
        url = "https://tieba.baidu.com/p/6935150237";

        WebCrawler crawler = new WebCrawler();

//        crawler.getWebHtml("http://tieba.baidu.com/photo/p?kw=%E5%B4%A9%E5%9D%8F3rd&flux=1&tid=6435170603&pic_id=ba4686b0cb13495490ebdf1e414e9258d0094a69&pn=1&fp=2&see_lz=1&red_tag=i1866022272");
//        crawler.downloadPic("http://tieba.baidu.com/photo/p?kw=%E5%B4%A9%E5%9D%8F3rd&flux=1&tid=6435170603&pic_id=ba4686b0cb13495490ebdf1e414e9258d0094a69&pn=1&fp=2&see_lz=1&red_tag=i1866022272",crawler.getParentPath(null));
        crawler.start(url);

        System.exit(0);
    }

    public void start(String url) {
        WebManager webManager = WebMannagerFactory.getWebManager(url);
        if (webManager != null) {
            String webHtml = getWebHtml(url);
            webManager.analysisHtml(webHtml);
            List<String> webImageUrls = getWebImageUrls(webHtml, IMAGE_PATTERN);
            while (StringUtils.isNotBlank(url = webManager.getNextPageUrl(url))) {
                webHtml = getWebHtml(url);
                for (String url1 : getWebImageUrls(webHtml, IMAGE_PATTERN)) {
                    if (!webImageUrls.contains(url1)) webImageUrls.add(url1);
                }
            }
            String parentPath = getParentPath(null);
            downloadPics(webImageUrls, parentPath);
//            List<String> originalImageUrls = webManager.getOriginalImageUrls(webImageUrls);
//            if (CollectionUtils.isNotEmpty(originalImageUrls)) {
//                downloadPics(originalImageUrls, getParentPath(ORI_DIR));
//            }
        }
    }

    public void defaultCrawler(String url) {
        String result = getWebHtml(url);
        List<String> webImageUrls = getWebImageUrls(result, IMAGE_PATTERN);
        downloadPics(webImageUrls);
    }

    /**
     * 获取网页html
     *
     * @param url
     * @return
     */
    public String getWebHtml(String url) {
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            System.out.println("url: " + url);
            System.out.println("开始抓取页面数据.... ");
            URLConnection connection = new URL(url).openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("Access denied!!");
            e.printStackTrace();
        } finally {
            Util.closeCloseable(in);
        }
        return result.toString();
    }

    /**
     * 提取网页中的图片url
     *
     * @param html
     * @param regex
     * @return
     */
    public List<String> getWebImageUrls(String html, String regex) {
//        System.out.println("开始匹配图片url: " + html);
        List<String> imageUrls = new ArrayList<>();
        if (StringUtils.isNotBlank(html)) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                String imageUrl = matcher.group(1);
                if (WebUtil.validImageUrl(imageUrl) && !imageUrls.contains(imageUrl)) {
                    imageUrls.add(imageUrl);
                }
            }
            System.out.println("已收集本页所有图片(" + imageUrls.size() + ")");
        }
        return imageUrls;
    }

    private static String getFormatByImageUrl(String imageUrl) {
        if (StringUtils.isNotBlank(imageUrl)) {
            int indexOf = imageUrl.lastIndexOf(".");
            String format = imageUrl.substring(indexOf + 1, imageUrl.length());
            if (IMAGE_FORMATS.contains(SymbolCons.COMMA + format + SymbolCons.COMMA)) {
                return SymbolCons.POINT + format.toLowerCase();
            }
        }
        return DEFAULT_FORMAT;
    }

    private void downloadPics(List<String> imageUrls, String parentPath) {
        System.out.println("开始下载以下图片(" + imageUrls.size() + "): ");
        Util.printlnCollection(imageUrls);
        imageUrls.stream()
                .distinct()
                .forEach(url -> {
                    downloadPic(url, parentPath + "\\" + StringUtil.getRandomStr(32));
                });

        //下载完之后弹出文件夹
        Util.popUpDir(parentPath);
    }

    /**
     * 下载图片到本地
     *
     * @param imageUrl 图片url
     * @param filePath 对应本地路径(不包含扩展名)
     */
    private void downloadPic(String imageUrl, String filePath) {
        filePath += getFormatByImageUrl(imageUrl);
        try (DataInputStream dis = new DataInputStream(new URL(imageUrl).openStream());
             FileOutputStream fos = new FileOutputStream(new File(filePath));
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = dis.read(buffer)) >= 0) {
//                System.out.println(new String(buffer));
                bos.write(buffer, 0, len);
            }
            fos.write(bos.toByteArray());
        } catch (Exception e) {
            System.out.println("图片下载失败! imageUrl: " + imageUrl + " filePath: " + filePath);
            e.printStackTrace();
        }
    }

    private void downloadPics(List<String> imageUrls) {
        downloadPics(imageUrls, getParentPath(null));
    }

    private String getParentPath(String suffix) {
        String parentPath = SAVE_PATH + StringUtil.getCurrentDateFormatString();
        if (StringUtils.isNotEmpty(suffix)) parentPath += "\\" + suffix;
        File file = new File(parentPath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                System.out.println("cannot mkfile!! path: " + parentPath);
            }
        }
        return parentPath;
    }
}
