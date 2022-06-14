package com.moon.java8action.chapter9;

public interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}
