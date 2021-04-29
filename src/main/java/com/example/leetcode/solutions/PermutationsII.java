package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2020/1/14 16:42
 * @Description: 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 */
public class PermutationsII {

    public static void main(String[] args) {
        int[] nums = {3, 3, 0, 3};
        System.out.println(permute1(nums));
    }

    /**
     * 思考:使用回溯递归找出每一种排列
     * 与上一题类似,但由于数字重复可能出现重复答案,因此这里关键是排重
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length > 0) {
            Arrays.sort(nums);
            search(nums, result, new boolean[nums.length], new ArrayList<>());
        }
        return result;
    }

    /**
     * Depth-First-Search,深度优先搜索法,优先搜索最深层
     * 使用回溯找出每一种排列
     * 需要合理剪枝筛选掉可能重复的答案
     *
     * @param nums
     * @param result
     * @param used   以空间换时间,标记每个数字是否被用过
     * @param temp   收集每一组可能的组合
     */
    @Score(time = Score.S.SS,memory = Score.S.SS)
    private static void search(int[] nums, List<List<Integer>> result, boolean[] used, List<Integer> temp) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //如果当前节点与上个节点状态一致并且上个节点已经统计结束则跳过此节点
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            if (!used[i]) {
                temp.add(nums[i]);
                used[i] = true;
                search(nums, result, used, temp);
                //切换分支
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }
}
