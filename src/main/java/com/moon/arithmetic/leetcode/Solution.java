package com.moon.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yujiangtao
 * @date 2018/5/7 10:04
 */
class Solution {

    private String currResult = "";
    private Map<String, Character> currChars = null;

    public int lengthOfLongestSubstring(String s) {
        String result = "";
        for(int i = 0; i < s.length(); i++) {
            if(s.length() - i < result.length()) {
                break;
            }
            String str = getLongestFromStart(s, i);
            if(str.length() > result.length()) {
                result = str;
                currResult = result;
            }
        }
        return result.length();
    }

    private String getLongestFromStart(String input, int startIndex) {
        Map<String, Character> occurChars = new HashMap<String, Character>();
        String str = "";
        if(startIndex != 0) {
            // 读取上次的记录
            occurChars = currChars;
            occurChars.remove(String.valueOf(input.charAt(startIndex - 1)));
            // 重置startIndex
            startIndex = startIndex + occurChars.size();
            //
            str = currResult.substring(1);
        }

        // 入参检查
        if(input == null || "".equals(input) || startIndex >= input.length()) {
            throw new IllegalArgumentException();
        }

        boolean isExist = true;
        while(isExist && startIndex < input.length()) {
            char singleChar = input.charAt(startIndex);
            if(occurChars.containsKey(String.valueOf(singleChar))) {
                currChars = occurChars;
                currResult = str;
                break;
            } else {
                // 加入到出现过的集合中，
                occurChars.put(String.valueOf(singleChar), singleChar);
                str += singleChar;
                startIndex ++;
            }
        }
        return str;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int len = new Solution().lengthOfLongestSubstring("anviaj");
        long end = System.currentTimeMillis();
        System.out.println(end -start);
        System.out.println(len);
    }
}