package com.moon.arithmetic.stack;


/**
 * 以数组实现的栈
 *
 * @author yujiangtao
 * @date 2020/10/10 下午3:57
 */
public class ArrayStack<T> {

    private int capacity;

    private int stackTop = -1;

    private Object[] arr;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.arr = new Object[capacity];
    }

    public void push(T t) {
        if(stackTop >= capacity - 1) {
            throw new RuntimeException("stack is full!");
        }
        stackTop ++;
        arr[stackTop] = t;
    }

    public T pop() {
        if(stackTop < 0) {
            throw new RuntimeException("stack is empty!");
        }
        T result = (T) arr[stackTop];
        arr[stackTop] = null;
        stackTop --;
        return result;
    }

    public void printStack() {
        for (int i = stackTop; i >= 0; i--) {
            System.out.printf("|%-20s|\n", arr[i]);
            System.out.println(" --------------------");
        }
    }
}
