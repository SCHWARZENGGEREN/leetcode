package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2019/11/7 14:51
 * @Description: 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 * 注意：整数顺序将表示为一个字符串。
 * <p>
 * 示例 1:
 * 输入: 1
 * 输出: "1"
 * <p>
 * 示例 2:
 * 输入: 4
 * 输出: "1211"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-and-say
 */
public class CountAndSay {

    /**
     * 遍历char的两种方式:
     * 1,new StringBuilder().toString().toCharArray().foreach{}
     * 2,new StringBuilder().charAt(0)
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 1;i<=30;i++){
            System.out.println(countAndSay(i));;
        }
    }

    /**
     * 用报数的方式描述前一个人的报数
     * 1 -> 1个1 -> 11
     * 11 -> 2个1 21
     * 21 -> 1个21个1 -> 1211
     *
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS,memory = Score.S.SS)
    public static String countAndSay(int n) {
        return recursiveCount(--n, new StringBuilder("1"), new StringBuilder());
    }

    private static String recursiveCount(int n, StringBuilder last, StringBuilder temp) {
        if (n > 0) {
            char lastCh = last.charAt(0);
            int count = 0;
            for (int i = 0; i < last.length(); i++) {
                char _this = last.charAt(i);
                if (_this == lastCh) {
                    count++;
                } else {
                    temp.append(count).append(lastCh);
                    lastCh = _this;
                    count = 1;
                }
                if (i == last.length() - 1) {
                    temp.append(count).append(lastCh);
                }
            }
            last.setLength(0);
            return recursiveCount(--n, temp, last);
        }
        return last.toString();
    }
}
