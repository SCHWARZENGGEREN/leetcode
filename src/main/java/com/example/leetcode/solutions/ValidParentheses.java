package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.Stack;

/**
 * @Auther: Rxh
 * @Date: 2019/11/18 10:05
 * @Description: 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * 输入: "()"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 * <p>
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 * <p>
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 * <p>
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 */
public class ValidParentheses {

    public static void main(String[] args) {
        String s = "(([]{()}))";
//        s = "([)]";
        System.out.println(isValid2(s));

//        System.out.println((int) '(');
//        System.out.println((int) ')');
//        System.out.println((int) '[');
//        System.out.println((int) ']');
//        System.out.println((int) '{');
//        System.out.println((int) '}');
    }

    /**
     * 虽然可以统计三种括号数量,但无法记录位置
     *
     * @param s
     * @return
     */
    public static boolean isValid1(String s) {
        if (s != null && s.length() > 0 && s.length() % 2 == 0) {
            //分别用三个变量记录三种括号数量变化,如果任何一个三种left必须>=0,才能形成闭合
            int len = s.length(), br_1_left = 0, br_2_left = 0, br_3_left = 0;
            for (int i = 0; i < len; i++) {
                char ch = s.charAt(i);
                if (ch == '(') {
                    br_1_left++;
                } else if (ch == ')') {
                    br_1_left--;
                } else if (ch == '[') {
                    br_2_left++;
                } else if (ch == ']') {
                    br_2_left--;
                } else if (ch == '{') {
                    br_3_left++;
                } else if (ch == '}') {
                    br_3_left--;
                }
                if (br_1_left < 0 || br_2_left < 0 || br_3_left < 0) return false;
            }
            return br_1_left == 0 && br_2_left == 0 && br_3_left == 0;
        }
        return false;
    }

    /**
     * 使用栈结构保存每一个遍历到的括号,遇到开括号就添加,闭括号就移除栈顶的开括号
     * 如果字符符合要求,那么每次移除的括号类型都匹配而且遍历完之后栈内没有剩余的括号
     *
     * @param s
     * @return
     */
    @Score(time = Score.S.SS, memory = Score.S.S)
    public static boolean isValid2(String s) {
        int len = s.length();
        if (len % 2 == 0) {
            Stack<Character> brackets = new Stack<>();
            for (int i = 0; i < len; i++) {
                char ch = s.charAt(i);
                if (ch == '(' || ch == '[' || ch == '{') {
                    brackets.push(ch);
                } else {
                    //观察char规律可知:)-(=1;]-[=2;}-{=2
                    if (!brackets.empty() && Math.abs(ch - brackets.peek()) <= 2) {
                        brackets.pop();
                    } else {
                        return false;
                    }
                }
            }
            return brackets.empty();
        }
        return false;
    }
}
