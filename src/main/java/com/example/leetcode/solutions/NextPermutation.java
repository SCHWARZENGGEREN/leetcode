package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;
import com.example.leetcode.common.anno.Unsettled;

import java.util.Arrays;

/**
 * @Auther: Rxh
 * @Date: 2019/11/15 10:41
 * @Description: 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * TIPS:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 */
@Unsettled
public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 3, 3};
        System.out.println(Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * [2021-3-10 15:34:41]
     * 将输入数组连成一个数字,找出所有组合数字中排在当前之后的那个
     * <p>
     * 已知最小排列是顺序,最大排列是逆序,那么从后向前遍历,只要出现了顺序的情况,就进行调整:
     * 找出后面队列中大于当前数字的最小数,并与当前数字进行替换,替换后对后面的队列进行排序即可
     * <p>
     * 0,排序
     * 1,找出最小值替换;
     * 2,将替换值排到正确的位置
     * 笑哭
     *
     * @param nums
     */
    @Score(time = Score.S.SS, memory = Score.S.C)
    public static void nextPermutation(int[] nums) {
        if (nums.length > 1) {
            int len = nums.length, j = len - 2, minPos, minOrTmp;
            //找到出现顺序的店
            while (j >= 0) {
                if (nums[j] < nums[j + 1])
                    break;
                j--;
            }
            //在顺序点后续队列中寻找(current,next]区间的最小值并替换
            if (j >= 0) {
                int i = j + 1;
                minOrTmp = nums[minPos = i];
                i++;
                while (i < len) {
                    if (nums[i] < nums[j])
                        break;
                    if (nums[i] > nums[j] && nums[i] <= minOrTmp) {
                        minOrTmp = nums[minPos = i];
                    }
                    i++;
                }
                nums[minPos] = nums[j];
                nums[j] = minOrTmp;
            }

            //j = -1说明没有任何两个数字是顺序排列/j>0则将节点之后的数字逆序处理
            reverseNumArray(nums, j + 1, len - 1);
        }
    }

    private static void reverseNumArray(int[] nums, int start, int end) {
        if (start < end) {
            for (int i = 0, temp; i <= (end - start) / 2; i++) {
                temp = nums[start + i];
                nums[start + i] = nums[end - i];
                nums[end - i] = temp;
            }
        }
    }
}
