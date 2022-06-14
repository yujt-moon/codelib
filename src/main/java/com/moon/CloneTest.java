package com.moon;

/**
 * @author yujiangtao
 * @date 2021/5/19 下午11:05
 */
public class CloneTest {

    private static class Class {

        private int num;
    }

    private static class Stu extends Class {
        private String name;

        private int age;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
