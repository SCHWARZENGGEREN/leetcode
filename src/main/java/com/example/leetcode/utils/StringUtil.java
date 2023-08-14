package com.example.leetcode.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Auther: Rxh
 * @Date: 2019/10/30 11:22
 * @Description:
 */
public class StringUtil {
    public static final String NUM_FORMAT = "[0-9]*";

    public static String getDuplicateStr(int count, String str) {
        StringBuilder sb = new StringBuilder();
        while (count-- > 0) {
            sb.append(str);
        }
        return sb.toString();
    }

    private static void testString() {
        System.out.println("start: " + System.currentTimeMillis());


        String _1 = "hello";
        String _2 = "world";
        String _3 = new String("hello") + new String("world");
        String _4 = new String("hello");
        String _5 = new String("world");
        String _6 = "helloworld";
        System.out.println(_1 == _4);
        System.out.println(_3 == _6);


        System.out.println("  end: " + System.currentTimeMillis());
    }

    /**
     * 以连接符拆解字符并转换为驼峰
     *
     * @param appendName
     * @param append
     * @return
     */
    public static String getHumpName(String appendName, String append) {
        String[] split = appendName.split(append);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String subStr = split[i];
            if (subStr != null && subStr.length() > 0) {
                if (i == split.length - 1
                        && (StringUtils.equals(subStr, "i") ||
                        StringUtils.equals(subStr, "ii") ||
                        StringUtils.equals(subStr, "iii"))) {
                    sb.append(subStr.toUpperCase());
                } else {
                    sb.append(subStr.replaceFirst(String.valueOf(subStr.charAt(0)), String.valueOf(subStr.charAt(0)).toUpperCase()));
                }
            }

        }
        return sb.toString();
    }

    public static String getAppendName(String humpName, String append) {
        char[] chars = humpName.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (Character.isUpperCase(ch)) {
                if (i > 0) sb.append(append);
                sb.append(Character.toLowerCase(ch));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String append = "-";
        String hump = "merge-two-binary-trees";
        System.out.println(getHumpName(hump, append));
        getAllLetters();
    }

    public static String getRandomStr(int size) {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid.substring(0, size);
    }

    public static String getCurrentDateFormatString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static boolean isNumric(String string) {
        if (StringUtils.isBlank(string)) {
            return true;
        }
        Pattern pattern = Pattern.compile(NUM_FORMAT);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }


    public static String unicodetoString(String unicode) {
        if (unicode == null || "".equals(unicode)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;
        while ((i = unicode.indexOf("\\u", pos)) != -1) {
            sb.append(unicode, pos, i);
            if (i + 5 < unicode.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
            }
        }
        return sb.append(unicode.substring(pos)).toString();
    }

    /**
     * 打印所有字母
     */
    public static void getAllLetters() {
        List<Character> characters = new ArrayList<>();
        int i = 0;
        while (i < 26) {
            characters.add((char) (i + 'a'));
            i++;
        }
        System.out.println(
                characters.stream()
                        .map(String::valueOf)
                        .collect(
                                Collectors.joining()
                        ));
        System.out.println(
                characters.stream()
                        .map(String::valueOf)
                        .collect(
                                Collectors.joining("\',\'", "\'", "\'")
                        ));
    }

}
