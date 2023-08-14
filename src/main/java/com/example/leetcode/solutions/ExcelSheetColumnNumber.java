package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

/**
 * @Auther: Rxh
 * @Date: 2021/7/30 14:33
 * @Description: 171. Excel 表列序号
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
 * <p>
 * 例如，
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * 示例 1:
 * 输入: columnTitle = "A"
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: columnTitle = "AB"
 * 输出: 28
 * <p>
 * 示例 3:
 * 输入: columnTitle = "ZY"
 * 输出: 701
 * <p>
 * 示例 4:
 * 输入: columnTitle = "FXSHRXW"
 * 输出: 2147483647
 * <p>
 * 提示：
 * <p>
 * 1 <= columnTitle.length <= 7
 * columnTitle 仅由大写英文组成
 * columnTitle 在范围 ["A", "FXSHRXW"] 内
 */
public class ExcelSheetColumnNumber {


    public static void main(String[] args) {
        System.out.println(titleToNumber("AB"));
        System.out.println(titleToNumber("FXSHRXW"));
    }

    /**
     * 26进制?
     *
     * @param columnTitle
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.B)
    public static int titleToNumber(String columnTitle) {
        int dig = 1, res = 0;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            res += (columnTitle.charAt(i) - '@') * dig;
            dig *= 26;
        }

        return res;
    }

}
