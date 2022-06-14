package com.moon.arithmetic.stack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 简单计算器的实现
 *
 * @author yujiangtao
 * @date 2020/10/10 下午3:56
 */
public class Caculator {

    private char plus = '+';

    private char minus = '-';

    private char multiply = '*';

    private char divide = '/';

    // TODO
    public static int caculate(String expression) {
        ArrayStack<Integer> numbers = new ArrayStack<>(20);
        ArrayStack<String> operators = new ArrayStack<>(10);

        // 去除表达式中的空格
        expression = expression.replaceAll("\\s", "");

        // 匹配数字
        String pattern = "(\\d)";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(expression);
        int group = 0;
        while(matcher.find()) {
            System.out.println(matcher.group(group));
            group++;
        }

        return 0;
    }

    public static void main(String[] args) {
        int caculate = caculate("34 +13* 9+44-12/3");
    }
}
