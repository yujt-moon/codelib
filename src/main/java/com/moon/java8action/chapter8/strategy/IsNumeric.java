package com.moon.java8action.chapter8.strategy;

import com.moon.java8action.chapter8.strategy.ValidationStrategy;

public class IsNumeric implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
