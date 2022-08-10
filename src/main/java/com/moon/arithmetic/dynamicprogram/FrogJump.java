package com.moon.arithmetic.dynamicprogram;

import java.util.Arrays;

/**
 * @author yujiangtaoa
 * @date 2022/8/3 下午12:25
 */
public class FrogJump {

    public static void main(String[] args) {
//        int[] a = {2, 3, 1, 1, 4};
//        int index = 3;
        int[] a = {3, 2, 1, 0, 4};
        int index = 3;
        System.out.println(frogJump(a, index));
    }

    // TODO
    public static boolean frogJump(int[] a, int index) {
        if (index > a.length) {
            return false;
        }
        boolean[] f = new boolean[a.length];
        Arrays.fill(f, false);
        f[0] = true;

        for (int i = 0; i < a.length; i++) {
            if (a[i] + i < a.length) {
                f[a[i] + i] = true;
            }
        }

        return (a[a.length - index -1] > a.length - index) && f[index];
    }

    public boolean canJump(int[] a) {
        int n = a.length;
        boolean[] f = new boolean[n];
        f[0] = true; // initialization

        for (int i = 1; i < n; i++) {
            f[i] = false;
            // previous stone j
            // last jump is from j to i
            for (int j = 0; j < i; j++) {
                if(f[j] && j + a[j] >= i) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n-1];
    }
}
