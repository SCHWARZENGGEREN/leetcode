package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2019/8/28 10:41
 * @Description: 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R     0   4   8    12
 * E T O E S I I G   1 3 5 7 9 11 13 15
 * E   D   H   N     2   6   10   14
 * <p>
 * 0 2 4 6 8 10 12 14
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如：
 * LCIRETOESIIGEDHN
 *
 * <p>
 * 例2:
 * L     D     R   0   6    12
 * E   O E   I I   1 5 7 11 13
 * E C   I H   N   2 4 8 10 14
 * T     S     G   3   9    15
 * <p>
 * LDREOEIIECIHNTSG
 * 0 3 6 9 12 15
 */
public class ZigzagConversion {

    public static void main(String[] args) {
        String source = "LEETCODEISHIRING";
        System.out.println(S1.convert(source, 4));
        System.out.println(S2.convert(source, 4));
    }

    public static class S1 {

        /**
         * 思路 ,将原来的字符拆成单个字符,然后逐行按照规则拼接字符,最后将拼好的字符拼接成一个字符
         *
         * @param s
         * @param numRows
         * @return
         */
        @Score(time = Score.S.A, memory = Score.S.A)
        public static String convert(String s, int numRows) {
            String result = null;
            if (s != null && numRows > 0) {
                int len = s.length();
                if (numRows == 1 || s.length() <= numRows) return s;
                List<StringBuilder> subChars = new ArrayList<>();
                int numRowsTmp = numRows;
                while (numRowsTmp-- > 0) {
                    subChars.add(new StringBuilder());
                }

                boolean forward = false;//正向还是反向
                int subIdx;
                for (int i = 0; i < len; i++) {
                    char c = s.charAt(i);
                    int loopIdx = i % (numRows - 1);
                    if (loopIdx == 0) forward = !forward;
                    if (loopIdx == 0 && !forward) {
                        subIdx = subChars.size() - 1;
                    } else {
                        subIdx = forward ? loopIdx : (numRows - 1 - loopIdx);
                    }
                    subChars.get(subIdx).append(c);
                }

                StringBuilder buffer = new StringBuilder();
                numRowsTmp = 0;
                while (numRowsTmp < numRows) {
                    buffer.append(subChars.get(numRowsTmp++));
                }
                result = buffer.toString();
            }
            return result;
        }
    }

    public static class S2 {

        /**
         * 分析每个子串每个角标与母串对应位置角标的关系并一一填充
         * 最上的横线和最下的横线分别对应(row-1)偶数整倍数与奇数整倍数对应的角标;
         * 偶数整倍数点到奇数整倍数点之间的则分布在
         * <p>
         * L     D     R   0    6     12
         * E   O E   I I   1  5 7  11 13
         * E C   I H   N   2 4  8 10  14
         * T     S     G   3    9     15
         * <p>
         *
         * 之字形
         * 初始化第一条竖线与第一条斜线
         *
         * @param s
         * @param numRows
         * @return
         */
        @Score(time = Score.S.SS,memory = Score.S.SS)
        public static String convert(String s, int numRows) {
            String result = null;
            if (s != null && numRows > 0) {
                if (numRows == 1 || s.length() <= numRows) return s;

                int len = s.length();
                char[] chars = s.toCharArray();
                char[] targetChars = new char[len];
                int[] targetIdxs = new int[len];

                //建立原字符串字符跟目标字符串字符之间的映射关系
                int targetIdx = 0;
                int loop = numRows - 1;
                for (int i = 0; i <= loop; i++) {
                    int j = i;
                    while (j < len) {
                        targetIdxs[targetIdx] = j;
                        targetChars[targetIdx] = chars[j];
                        if (i == 0 || i == loop) {
                            //首or尾
                            j += loop * 2;
                        } else {
                            //中间
                            if (j < loop * 2) {
                                //起始竖线:走势向下,与右侧线差距逐渐变小;起始斜线:走势向上,与右侧线差距逐渐变大
                                j += 2 * (j < loop ? loop - i : i);
                            } else {
                                //直接往后叠
                                j = targetIdxs[targetIdx - 1] + loop * 2;
                            }
                        }
                        targetIdx++;
                    }
                }
                result = String.valueOf(targetChars);
            }
            return result;
        }
    }
}
