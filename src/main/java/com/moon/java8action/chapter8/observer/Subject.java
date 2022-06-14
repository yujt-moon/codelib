package com.moon.java8action.chapter8.observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
