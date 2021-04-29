package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2020/12/7 15:35
 * @Description: 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * https://leetcode-cn.com/problems/pascals-triangle/
 */
public class PascalsTriangle {

    public static void main(String[] args) {
        generate(8).forEach(System.out::println);
    }

    /**
     * TODO list替换为array
     * @param numRows
     * @return
     */
    @Score(time = Score.S.SSS,memory = Score.S.A)
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        List<Integer> last = new ArrayList<>(), current;
        if (numRows > 0) {
            while (numRows-- > 0) {
                current = new ArrayList<>();
                int lastSize = last.size(), index = 0;
                while (index <= lastSize) {
                    //首尾1
                    if (index == 0 || index == lastSize)
                        current.add(1);
                        //中间部分计算
                    else
                        current.add(
                                safeGet(last, index - 1) +
                                        safeGet(last, index)
                        );
                    index++;
                }
                last = current;
                result.add(current);
            }
        }
        return result;
    }

    private static Integer safeGet(List<Integer> list, int idx) {
        if (idx >= list.size() || idx < 0)
            return 0;
        return list.get(idx);
    }
}
