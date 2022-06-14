package com.moon.java8action.chapter8.strategy;

import com.moon.java8action.chapter8.strategy.ValidationStrategy;

public class Validator {

    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}
