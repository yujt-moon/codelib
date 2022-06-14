package com.moon;

/**
 * @author yujiangtao
 * @date 2021/8/17 下午9:48
 */
public enum Operation {
    PLUS {
        @Override
        double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        @Override
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        @Override
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        double apply(double x, double y) {
            return x / y;
        }
    };

    abstract double apply(double x, double y);
}
