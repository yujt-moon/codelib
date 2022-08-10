package com.moon;

import java.util.Stack;

/**
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，
 * 判断字符串是否有效。 有效字符串需满足： 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。 示例 1：输入：s = "()" 输出：true 示例 2：输入：s = "()[]{}" 输出：true
 * 示例 3：输入：s = "[(){}]" 输出：true
 *
 *
 * @author yujiangtaoa
 * @date 2022/8/3 下午6:43
 */
public class Solution2 {

    public static void main(String[] args) {
//        String s = "[(){}]";
//        String s = "[(){}]{}({)}";
        String s = "[(){}]{}({[]{()}})";
        System.out.println(isValid(s));
    }

    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.empty()) {
                    return false;
                }
                char poped = stack.pop();
                if(ch == ')' && poped != '(') {
                    return false;
                }
                if (ch == ']' && poped != '[') {
                    return false;
                }
                if(ch == '}' && poped != '{') {
                    return false;
                }
            }
        }
        return true;
    }
}
