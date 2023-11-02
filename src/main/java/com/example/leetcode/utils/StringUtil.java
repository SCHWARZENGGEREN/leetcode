package com.example.leetcode.utils;

import com.example.leetcode.constants.CommonCsts;
import com.example.leetcode.constants.SepConsts;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Auther: Rxh
 * @Date: 2019/10/30 11:22
 * @Description:
 */
public class StringUtil {
    public static final Pattern WHITE_LIST = Pattern.compile("^/([a-z0-9]+?)/(home|index|help|login|logout|logging|404|error|account/reset-self-password|auth-code|no-token/\\S+|cmp_sp/\\S+)?$");
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

    private static final List<String> ROMAN_NUMS = Arrays.asList(
            "i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix", "x"
    );

    /**
     * 以连接符拆解字符并转换为驼峰
     * 自动转为小写
     *
     * @param appendName
     * @param append
     * @return
     */
    public static String getHumpName(String appendName, String append) {
        String[] split = appendName.toLowerCase().split(append);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String subStr = split[i];
            if (subStr != null && subStr.length() > 0) {
                if (i == split.length - 1
                        && ROMAN_NUMS.contains(subStr)) {
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
        String hump = "rings-and-rods";
        System.out.println(getHumpName(hump, SepConsts.MIDLINE));
        System.out.println(getLeetcodeUrl("MovePiecesToObtainAString"));
//        getAllLetters();

//        System.out.println(unicode(hump));

//        String logParam = "";
//        System.out.println(requestLog2Json(logParam));

//        printDateDemo();

        testRegex();
    }

    public static void testRegex() {
        String regex = "^[0-9Xx]\\d{18}";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        System.out.println(pattern.matcher("410726199110152419").matches());
        System.out.println(pattern.matcher("41072619911015241X").matches());
        System.out.println(pattern.matcher("41072619911015241S").matches());
        System.out.println(pattern.matcher("4107261991101524191").matches());
        System.out.println(pattern.matcher("4107261991101524x").matches());
    }

    private static void printDateDemo() {
        List<String> dates = new ArrayList<>();
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateDf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int i = 90;

        while (true) {
            String format = dateDf.format(now);
            dates.add(String.format("\"%s\"", format));
            if (format.equals("2023-12-04")) {
                break;
            }
            now = now.plusDays(1);
        }
        System.out.println(dates);
    }

    private static String getLeetcodeUrl(String solutionName) {
        StringBuilder sb = new StringBuilder(solutionName);
        int i = 1;
        while (i < sb.length()) {
            if (isUpperWords(sb.charAt(i))) {
                sb.insert(i, SepConsts.MIDLINE);
                i++;
            }
            i++;
        }
        return String.format(
                "https://leetcode.cn/problems/%s/",
                sb.toString().toLowerCase()
        );
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

    public static String unicode(String string) {
        if (string == null || "".equals(string)) {
            return null;
        }
        String hex;
        StringBuilder sb = new StringBuilder();
        for (char c : string.toCharArray()) {
            hex = Integer.toHexString((int) c);
            sb.append("\\u")
                    .append(getDuplicateStr(4 - hex.length(), "0"))
                    .append(hex);
        }
        return sb.toString();
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

    /**
     * @return
     */
    public static String requestLog2Json(String requestLog) {
        String[] split = requestLog.split(", ");
        StringBuilder sb = new StringBuilder("{");
        for (String s : split) {
            String[] split1 = s.split("=");
            sb.append("\"")
                    .append(split1[0])
                    .append("\"")
                    .append(":");

            if (!isNumric(split1[1])) {
                sb.append("\"");
            }
            sb.append(split1[1]);
            if (!isNumric(split1[1])) {
                sb.append("\"");
            }
            sb.append(",");
        }
        return sb
                .replace(sb.length() - 1, sb.length(), "}")
                .toString();
    }

    public static boolean isUpperWords(char c) {
        return c >= CommonCsts.CHAR_A_UPPER && c <= CommonCsts.CHAR_Z_UPPER;
    }

    public static boolean isLowerWords(char c) {
        return c >= CommonCsts.CHAR_A_LOWER && c <= CommonCsts.CHAR_Z_LOWER;
    }
}
