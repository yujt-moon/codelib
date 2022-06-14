package com.moon.arithmetic.stack;

import org.junit.Test;

/**
 * @author yujiangtao
 * @date 2020/10/11 下午1:40
 */
public class ArrayStackTest {

    @Test
    public void testPrint() {
        ArrayStack<String> stack = new ArrayStack<>(5);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        stack.pop();
        stack.printStack();
    }
}
