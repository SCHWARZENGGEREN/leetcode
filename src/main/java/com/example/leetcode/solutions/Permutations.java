package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2020/1/11 10:06
 * @Description: 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 链接：https://leetcode-cn.com/problems/permutations
 */
public class Permutations {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(permute1(nums));
    }

    /**
     * 思考:使用回溯递归找出每一种排列,但这种可能消耗内存较多
     *
     * @param nums
     * @return
     */
    @Score(time = Score.S.SSS,memory = Score.S.S)
    public static List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length > 0) {
            search(nums, result, new boolean[nums.length], new ArrayList<>());
        }
        return result;
    }

    /**
     * Depth-First-Search,深度优先搜索法,优先搜索最深层
     * 使用回溯找出每一种排列
     * 这里不需要剪枝,因为使用了数组记录每个数字使用情况,所以每个分支都能到底
     * @param nums
     * @param result
     * @param used   以空间换时间,标记每个数字是否被用过
     * @param temp   收集每一组可能的组合
     */
    private static void search(int[] nums, List<List<Integer>> result, boolean[] used, List<Integer> temp) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
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
