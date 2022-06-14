package com.moon.arithmetic.recursive;

/**
 * @author yujiangtao
 * @date 2020/7/7 下午4:43
 */
public class Test {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println("simpleFib: " + fibonacci.simpleFib(10));

        System.out.println("fib: " + fibonacci.memoFib(10));

        System.out.println("fib: " + fibonacci.dynamicFib(10));
    }
}
