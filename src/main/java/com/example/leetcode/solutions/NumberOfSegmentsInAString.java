package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @Auther: Rxh
 * @Date: 2021/10/8 10:37
 * @Description: 434. 字符串中的单词数
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * <p>
 * 示例:
 * 输入: "Hello, my name is John"
 * 输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 */
public class NumberOfSegmentsInAString {

    public static void main(String[] args) {
        System.out.println(countSegments("The one-hour drama series Westworld is a dark odyssey about the dawn of artificial consciousness and the evolution of sin. Set at the intersection of the near future and the reimagined past, it explores a world in which every human appetite, no matter how noble or depraved, can be indulged."));
    }

    @Score(time = Score.S.SSS, memory = Score.S.C)
    public static int countSegments(String s) {
        if (s != null && s.length() > 0) {
            //效率低
//            return (int) Stream.of(s.split(" ")).filter(seg -> seg.length() > 0).count();
            String[] split = s.split(" ");
            ArrayList<String> segs = new ArrayList<>();
            for (String seg : split) {
                if (seg.length() > 0)
                    segs.add(seg);
            }
            return segs.size();
        }
        return 0;
    }
}
