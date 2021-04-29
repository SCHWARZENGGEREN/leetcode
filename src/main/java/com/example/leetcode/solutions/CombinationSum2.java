package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.*;

/**
 * @Auther: Rxh
 * @Date: 2019/11/1 15:24
 * @Description:
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        int[] candidates = {1, 1, 1, 1, 2, 2, 3};
        int target = 7;
        System.out.println("S1 " + combinationSum2_1(candidates, target));
//        System.out.println("S2 " + combinationSum2_2(candidates, target));
    }

    /**
     * 同样适用回溯搜寻数字组合,不同之处在于
     * 数字不完全重复
     * 如何去重?
     * 根据现有搜寻逻辑,如果上次匹配成功的组合的最后一个数字与当前相同,可以判定为重复组合吗?
     * 怎样保证去除数字可以重复且结果不重复
     * 在combinationSum1的基础上用hashset对结果进行去重,虽然能到到效果但耗时较多
     *
     * @param candidates
     * @param target
     * @return
     */
    @Score(time = Score.S.SS, memory = Score.S.SS)
    public static List<List<Integer>> combinationSum2_1(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        System.out.println(Arrays.toString(candidates));
        searchCombNums2_1(0, target, candidates, result, new ArrayList<>());
        return result;
    }

    private static void searchCombNums2_1(int pos, int target, int[] candidates, List<List<Integer>> result, List<Integer> branchRes) {
        for (int i = pos; i < candidates.length; i++) {
            int current = candidates[i];
            if (current > target) break;//排除无效分支
            if (current > target / 2 && current != target) continue;//下一个肯定不小于当前,所以当前节点无用,排除
            if (i > pos && current == candidates[i - 1]) continue;//通过i>pos说明pos位置搜索已结束,如果数字重复,那么当前节点搜索的答案肯定与上一个节点答案重复

            branchRes.add(current);
            if (current == target) {//匹配成功,剪枝
                result.add(new ArrayList<>(branchRes));
                branchRes.remove(branchRes.size() - 1);
                break;
            } else {//继续延伸分支
                searchCombNums2_1(i + 1, target - current, candidates, result, branchRes);
                branchRes.remove(branchRes.size() - 1);
            }
        }
    }


    /**
     * @param candidates
     * @param target
     * @return 如何判断某处节点会重复
     * 假设在某个节点处,下个元素值与它相等,而且当前节点向下搜寻过程中有不包含下个节点的答案,
     * 则说明遍历至下个节点探索出的答案全部是重复的
     */
    public static List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        // 先将数组排序，这一步很关键
        Arrays.sort(candidates);
        findCombinationSum2(candidates, 0, len, target, new Stack<>(), res);
        return res;
    }

    private static void findCombinationSum2(int[] candidates, int begin, int len, int residue, Stack<Integer> stack, List<List<Integer>> res) {
        if (residue == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = begin; i < len; i++) {
            // 为了避免将负数传递到下一个分支，这里剪枝
            if (residue - candidates[i] < 0) {
                break;
            }

//            // 相同部分剪枝
//            if (i > begin && candidates[i] == candidates[i - 1]) {
//                continue;
//            }

            stack.add(candidates[i]);
            findCombinationSum2(candidates, i + 1, len, residue - candidates[i], stack, res);
            stack.pop();
        }
    }

}
