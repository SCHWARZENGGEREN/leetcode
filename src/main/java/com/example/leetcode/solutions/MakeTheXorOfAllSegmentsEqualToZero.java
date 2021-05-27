package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Unsettled;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: Rxh
 * @Date: 2021/5/25 10:51
 * @Description: 1787. 使所有区间的异或结果为零
 * 给你一个整数数组 nums​​​ 和一个整数 k​​​​​ 。区间 [left, right]（left <= right）的 异或结果 是对下标位于 left 和 right
 * （包括 left 和 right ）之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。
 * 返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,0,3,0], k = 1
 * 输出：3
 * 解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
 * <p>
 * 示例 2：
 * 输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
 * 输出：3
 * 解释：将数组 [3,4,5,2,1,7,3,4,7]
 * 修改为 [3,4,7,3,4,7,3,4,7]
 * <p>
 * 示例 3：
 * 输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
 * 输出：3
 * 解释：将数组[1,2,4,1,2,5,1,2,6]
 * 修改为 [1,2,3,1,2,3,1,2,3]
 * <p>
 * 提示：
 * 1 <= k <= nums.length <= 2000
 * ​​​​​​0 <= nums[i] < 2^10
 * ⊕
 */
public class MakeTheXorOfAllSegmentsEqualToZero {

    public static void main(String[] args) {
        int[] nums = {26, 19, 19, 28, 13, 14, 6, 25, 28, 19, 0, 15, 25, 11};
        int k = 3;
        System.out.println(minChanges(nums, k));
    }

    /**
     * 修改数组使得取任意一个子数组,其异或累计运算结果为0,那么数组只能是[a,b,c,a,b,c,a,b.....]
     *
     * @param nums
     * @param k
     * @return
     */
    @Unsettled
    public static int minChanges(int[] nums, int k) {
        int subArr[] = new int[k];
        Set<Integer> subNums = new HashSet<>();
        int changeNum = 0, idx = 0, xor = 0;
        while (idx < k - 1) {
            subArr[idx] = nums[idx];
            xor ^= subArr[idx];
            idx++;
        }
        if (nums[idx] != xor) {
            subArr[k - 1] = xor;
            changeNum++;
        }
        System.out.println(Arrays.toString(subArr));
        //拿到单元子数组,对整个数组进行调整
        for (idx += 1; idx < nums.length; idx++) {
            if (nums[idx] != subArr[idx % k]) {
                changeNum++;
            }
        }
        return changeNum;
    }
}
