package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.HashMap;
import java.util.Map;

/**
 * @author renxinheng
 * @ClassName MostFrequentEvenElement
 * @createTime 2023/4/13
 * @desc 2404. 出现最频繁的偶数元素
 * 给你一个整数数组 nums ，返回出现最频繁的偶数元素。
 * 如果存在多个满足条件的元素，只需要返回 最小 的一个。如果不存在这样的元素，返回 -1 。
 * <p>
 * 示例 1：
 * 输入：nums = [0,1,2,2,4,4,1]
 * <p>
 * 输出：2
 * 解释：
 * 数组中的偶数元素为 0、2 和 4 ，在这些元素中，2 和 4 出现次数最多。
 * 返回最小的那个，即返回 2 。
 * <p>
 * 示例 2：
 * 输入：nums = [4,4,4,9,2,4]
 * 输出：4
 * 解释：4 是出现最频繁的偶数元素。
 * <p>
 * 示例 3：
 * 输入：nums = [29,47,21,41,13,37,25,7]
 * 输出：-1
 * 解释：不存在偶数元素。
 * <p>
 * 提示：
 * 1 <= nums.length <= 2000
 * 0 <= nums[i] <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/most-frequent-even-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MostFrequentEvenElement {

    public static void main(String[] args) {
//        int[] nums = {4, 4, 4, 9, 2, 4};
//        int[] nums = {29,47,21,41,13,37,25,7};
        int[] nums = {0, 1, 2, 2, 4, 4, 1};
        System.out.println(mostFrequentEven(nums));
    }

    /**
     * 出现次数最多的偶数
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.S, memory = Score.S.A)
    public static int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> numFreqMap = new HashMap<>();
        int maxCount = 0, maxCountNum = -1;
        for (int num : nums) {
            if (num % 2 == 0) {
                if (maxCountNum < 0) maxCountNum = num;
                int count = numFreqMap.getOrDefault(num, 0) + 1;
                numFreqMap.put(num, count);
                if (count > maxCount) {
                    maxCountNum = num;
                    maxCount = count;
                } else if (count == maxCount) {
                    if (maxCountNum > num)
                        maxCountNum = num;
                }
            }
        }
        return maxCount == 0 ? -1 : maxCountNum;
    }
}
