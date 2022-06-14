package com.moon.arithmetic.recursive;

/**
 * 斐波那契数列
 * @author yujiangtao
 * @date 2020/7/7 下午4:36
 */
public class Fibonacci {

    // 斐波那契的规律 f(n) = f(n-1) + f(n-2);
    //

    /**
     * 使用递归简单的处理
     * 缺点：子问题重复计算
     * @param n
     */
    public int simpleFib(int n) {
        if(n <= 2) {
            return 1;
        }
        return simpleFib(n-1) + simpleFib(n-2);
    }

    // 保存子问题的结果
    int[] calculated = new int[20];

    /**
     * 解决方法 A （记忆：memoization）
     * 将已计算过实例的结果制表备查
     *
     * 没有重复子问题的计算
     * @param n
     * @return
     */
    public int memoFib(int n) {
        if(n <= 2) {
            return 1;
        }
        int currValue = 0;
        if(calculated[n] == 0) {
            currValue = memoFib(n-1) + memoFib(n-2);
            calculated[n] = currValue;
        } else {
            currValue = calculated[n];
        }
        return currValue;
    }


    /**
     * 解决方法 B （动态规划：dynamic programming）
     * 颠倒计算方向：由自顶而下递归，为自底而上迭代
     *
     * @param n
     * @return
     */
    public int dynamicFib(int n) {
        int f = 0, g = 1; // fib(0), fib(1)
        if(n == 0) {
            return f;
        }
        while( 1 < n --) {
            g = g + f;
            f = g - f;
        }
        return g;
    }
}
