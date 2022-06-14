package com.moon;

import java.util.StringTokenizer;

/**
 * @author yujiangtao
 * @date 2018/6/23 16:34
 */
public class StringTokenizerTest {
    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer("Welcome to our country");
        while(st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }

        StringTokenizer st2 = new StringTokenizer("Welcome,to,our,country", ",");
        while (st2.hasMoreTokens()) {
            System.out.println(st2.nextToken());
        }
    }
}
