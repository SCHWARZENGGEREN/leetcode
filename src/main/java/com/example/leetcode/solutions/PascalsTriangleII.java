package com.example.leetcode.solutions;

import com.example.leetcode.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Rxh
 * @Date: 2020/12/7 15:35
 * @Description: 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 杨辉三角II,获取第k行
 * https://leetcode-cn.com/problems/pascals-triangle/
 */
public class PascalsTriangleII {

    public static void main(String[] args) {
        printBetter();
    }

    private static void printBetter() {
        List<List<String>> pascalsNums = new ArrayList<>();
        List<String> printList = new ArrayList<>();
        int height = 15;
        for (int i = 0; i <= height; i++) {
            pascalsNums.add(getRow(i).stream().map(String::valueOf).collect(Collectors.toList()));
        }

        pascalsNums.forEach(System.out::println);

        int maxNumLen = pascalsNums.get(pascalsNums.size() - 1).get(pascalsNums.get(pascalsNums.size() - 1).size() / 2).length(),
                maxStrLen = 0;

        StringBuilder sb = new StringBuilder();
        for (int j = pascalsNums.size() - 1; j >= 0; j--) {
            sb.setLength(0);
            List<String> pascalsNumList = pascalsNums.get(j);
            for (String pasclasNum : pascalsNumList) {
                int blankCount = maxNumLen - pasclasNum.length();
                if (blankCount > 0) {
                    sb.append(StringUtil.getDuplicateStr((1 + blankCount) / 2, " "));
                }
                sb.append(pasclasNum);
                if (blankCount > 0) {
                    sb.append(StringUtil.getDuplicateStr(blankCount / 2, " "));
                }
                sb.append(",");
            }

            sb.setLength(sb.length() - 1);
            if (maxStrLen > sb.length()) {
                printList.add(StringUtil.getDuplicateStr((maxStrLen - sb.length()) / 2, " ") + sb.toString());
            } else {
                maxStrLen = Math.max(maxStrLen, sb.length());
                printList.add(sb.toString());
            }
        }

        for (int j = printList.size() - 1; j >= 0; j--) {
            System.out.println(printList.get(j));
        }
    }


    private static List<Integer> getRow(int rowIndex) {
        int numRows = rowIndex + 1;
        List<Integer> last = new ArrayList<>(), current = new ArrayList<>();
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
            }
        }

        return current;
    }

    private static Integer safeGet(List<Integer> list, int idx) {
        if (idx >= list.size() || idx < 0)
            return 0;
        return list.get(idx);
    }
}
