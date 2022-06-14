package com.moon.java8action.chapter9;

public interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    }
}
