package com.moon.arithmetic.recursive;

/**
 * 假如这里有 n 个台阶，每次你可以跨 1 个台阶或者 2 个台阶，请问走这 n 个台阶有多少种走法？
 * f(n) = f(n - 1) + f(n - 2)
 * f(1) = 1
 * f(2) = 2
 *
 * @author yujiangtao
 * @date 2020/10/11 下午4:56
 */
public class LadderQuestion {

    /**
     * 递归实现
     * @param n
     * @return
     */
    public static int getWayCount(int n) {
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        return getWayCount(n - 1) + getWayCount(n - 2);
    }

    /**
     * 循环实现
     * @param n
     * @return
     */
    public static int getWayCountLoop(int n) {
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }

        int prepre = 1;
        int pre = 2;
        int result = 0;
        for(int i = 3; i <= n; i++) {
            result = prepre + pre;
            prepre = pre;
            pre = result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getWayCount(7));
        System.out.println(getWayCountLoop(7));
    }
}
