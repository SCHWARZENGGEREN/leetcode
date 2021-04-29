package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2019/10/25 09:59
 * @Description: 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 *  
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * <p>
 * 示例 2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {1, 2, 3, 4, 5};
        int target = 7;
        System.out.println("S1 " + combinationSum1(candidates, target));
        System.out.println("S2 " + combinationSum2(candidates, target));
    }

    /**
     * 使用回溯思维
     *
     * @param candidates
     * @param target
     * @return
     */
    @Score(time = Score.S.SSS,memory = Score.S.SS)
    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        searchCombNums1(0, target, candidates, result, new ArrayList<>());
        return result;
    }
    private static void searchCombNums1(int pos, int target, int[] candidates, List<List<Integer>> result, List<Integer> branchRes) {
        for (int i = pos; i < candidates.length; i++) {
            int current = candidates[i];
            if (i > 0 && current == candidates[i - 1]) continue;//排除重复数字
            if (current > target) break;//排除无效分支
            if (current > target / 2 && current != target) continue;//下一个肯定不小于当前,所以当前节点无用,排除

            branchRes.add(current);
            if (current == target) {//匹配成功,剪枝
                result.add(new ArrayList<>(branchRes));
                branchRes.remove(branchRes.size() - 1);
                break;
            } else {//继续延伸分支
                searchCombNums1(i, target - current, candidates, result, branchRes);
                branchRes.remove(branchRes.size() - 1);
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/combination-sum/solution/xue-yi-tao-zou-tian-xia-hui-su-suan-fa-by-powcai/
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, res, 0, new ArrayList<>());
        return res;
    }
    private static void backtrack(int[] candidates, int target, List<List<Integer>> res, int i, ArrayList<Integer> tmp_list) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(tmp_list));
            return;
        }
        for (int start = i; start < candidates.length; start++) {
            if (target < candidates[start]) break;
            tmp_list.add(candidates[start]);
            backtrack(candidates, target - candidates[start], res, start, tmp_list);
            tmp_list.remove(tmp_list.size() - 1);
        }
    }
}
