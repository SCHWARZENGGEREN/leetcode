package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: Rxh
 * @Date: 2019/11/18 16:27
 * @Description: 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(4));
        System.out.println(generateParenthesis1(4));
    }

    /**
     * 使用回溯遍历每种排列
     * 使用StringBuilder与char[] 效率相当
     * @param n
     * @return
     */
    @Score(time = Score.S.A, memory = Score.S.SSS)
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n > 0) {
            char[] chars = new char[n * 2];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = ')';
            }
            generateParenthesis(result, chars, 0, 0, new Stack<>());
        }
        return result;
    }

    /**
     * 随机排列一半的开括号,然后填充另一半的闭括号
     *
     * @param generate
     * @param result
     * @param openBraCount 剩余要放置开括号的数量
     */
    private static void generateParenthesis(List<String> result, char[] generate, int pos, int openBraCount, Stack<Character> stack) {
        if (openBraCount >= generate.length / 2) {
            result.add(new String(generate));
            return;
        }
        //校验括号合理性 当前位置之前的闭括号数量不多于开括号
        //放置开括号
        stack.push('(');
        generate[pos] = '(';
        generateParenthesis(result, generate, pos + 1, openBraCount + 1, stack);
        generate[pos] = ')';
        stack.pop();
        //放置闭括号
        if (!stack.empty()) {
            stack.pop();
            generate[pos] = ')';
            generateParenthesis(result, generate, pos + 1, openBraCount, stack);
            stack.push('(');
        }
    }

    /**
     * 使用回溯遍历每种排列
     * @param n
     * @return
     */
    @Score(time = Score.S.SSS, memory = Score.S.SSS)
    public static List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        if (n > 0) {
            char[] chars = new char[n * 2];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = ')';
            }
            generateParenthesis1(result, chars, 0, 0,0);
        }
        return result;
    }

    /**
     * 使用int代替stack
     * @param generate
     * @param result
     * @param openBraCount 剩余要放置开括号的数量
     */
    private static void generateParenthesis1(List<String> result, char[] generate, int pos, int openBraCount,int currentLeftOpenCount) {
        if (openBraCount >= generate.length / 2) {
            result.add(new String(generate));
            return;
        }
        //校验括号合理性 当前位置之前的闭括号数量不多于开括号
        //放置开括号
        generate[pos] = '(';
        generateParenthesis1(result, generate, pos + 1, openBraCount + 1,currentLeftOpenCount+1);
        generate[pos] = ')';
        //放置闭括号
        if (currentLeftOpenCount > 0) {
            generate[pos] = ')';
            generateParenthesis1(result, generate, pos + 1, openBraCount,currentLeftOpenCount-1);
        }
    }

}
