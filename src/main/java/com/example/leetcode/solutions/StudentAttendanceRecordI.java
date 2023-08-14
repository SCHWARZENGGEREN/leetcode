package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/8/17 10:53
 * @Description: 551. 学生出勤记录 I
 * 给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * <p>
 * 'A'：Absent，缺勤
 * 'L'：Late，迟到
 * 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * <p>
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
 * 如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PPALLP"
 * 输出：true
 * 解释：学生缺勤次数少于 2 次，且不存在 3 天或以上的连续迟到记录。
 * 示例 2：
 * <p>
 * 输入：s = "PPALLL"
 * 输出：false
 * 解释：学生最后三天连续迟到，所以不满足出勤奖励的条件。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s[i] 为 'A'、'L' 或 'P'
 */
public class StudentAttendanceRecordI {
    public static void main(String[] args) {
        System.out.println(checkRecord("PPALLP"));
        System.out.println(checkRecord("PPALLL"));
    }

    /**
     * 思路一:按照缩减字符串的两种方式: 1,a1b2a1 2,a2b2 分别统计'A' 和 'L'
     * 那么统计A时只要满足总数<2;统计L时只要满足连续出现的L数<3
     *
     * @param s
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.S)
    public static boolean checkRecord(String s) {
        int[] recordSum = new int[2];
        if (s.length() >= 2) {
            for (char c : s.toCharArray()) {
                if (c == 'L') {
                    recordSum[1]++;
                } else {
                    recordSum[1] = 0;
                    if (c == 'A') {
                        recordSum[0]++;
                    }
                }
                if (recordSum[0] >= 2 || recordSum[1] >= 3) return false;
            }
        }
        return true;
    }
}
