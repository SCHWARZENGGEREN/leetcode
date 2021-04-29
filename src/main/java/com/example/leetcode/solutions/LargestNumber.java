package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @Auther: Rxh
 * @Date: 2021/4/12 10:13
 * @Description: 179. 最大数
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 * <p>
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出："1"
 * <p>
 * 示例 4：
 * 输入：nums = [10]
 * 输出："10"
 * <p>
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 * <p>
 * https://leetcode-cn.com/problems/largest-number/
 */
public class LargestNumber {

    public static void main(String[] args) {
        int[] nums = {3, 5, 54, 545, 5456};
        int[] nums1 = {34323, 3432};
        nums = nums1;
//        System.out.println(largestNumber(nums));
//        System.out.println(Arrays.toString(nums));
//        String numRes = Arrays.stream(nums)
//                .boxed()
//                .map(String::valueOf)
//                .sorted(new MyStrComparator())
//                .collect(Collectors.joining());
//
//        System.out.println(numRes);
    }

    /**
     * TODO 效率优化
     * @param nums
     * @return
     */
    @Score(time = Score.S.D, memory = Score.S.A)
    public static String largestNumber(int[] nums) {
        String res = Arrays.stream(nums).boxed()
                .map(String::valueOf)
                .sorted(new MyStrComparator())
                .collect(Collectors.joining());
        if (res.startsWith("0")) {
            res = "0";
        }
        return res;
    }

    /**
     * 自定义排序:
     * 1,数字倒叙;
     * 2,如果前面的数字相同,取长数字后面的数字:如果比前面的大,就排前面,否则拍后面
     * 如果后面的数字跟开头数字一致:不仅要跟上一个数字对比,还要跟其他数字对比
     */
    private static class MyStrComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int len1 = o1.length();
            int len2 = o2.length();
            int lim = Math.min(len1, len2);
            char v1[] = o1.toCharArray();
            char v2[] = o2.toCharArray();

            int k = 0;
            while (k < lim) {
                char c1 = v1[k];
                char c2 = v2[k];
                if (c1 != c2) {
                    return c2 - c1;
                }
                k++;
            }
            //对比长出来的后一位和前面最后一位
            if (len1 == len2) {
                return 0;
            } else {
                //由于回文串等等比较起来复杂,直接对比s1+s2和s2+s1
                return compare(o1 + o2, o2 + o1);
            }
        }
    }
}
