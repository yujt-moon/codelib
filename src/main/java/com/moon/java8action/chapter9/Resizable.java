package com.moon.java8action.chapter9;

public interface Resizable extends Drawable {
    int getWeight();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setAbsoluteSize(int width, int height);
}
