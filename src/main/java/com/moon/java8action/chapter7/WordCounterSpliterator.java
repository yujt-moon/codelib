package com.moon.java8action.chapter7;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;

    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        // 处理当前字符
        action.accept(string.charAt(currentChar++));
        // 如果还有字符要处理，则返回 true
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        // 返回 null 表示要解析的 String 已经足够小，可以顺序处理
        if(currentSize < 10) {
            return null;
        }
        // 将试探拆分位置设定为要解析的 String 的中间
        for(int splitPos = currentSize / 2 + currentChar;
            splitPos < string.length(); splitPos++) {
            // 让拆分位置前进到知道下一个空格
            if(Character.isWhitespace(string.charAt(splitPos))) {
                // 创建一个新 WordCounterSpliterator 来解析 String 从开始到拆分位置的部分
                WordCounterSpliterator spliterator =
                        new WordCounterSpliterator(string.substring(currentChar, splitPos));
                // 将这个 WordCounterSpliterator 的其实位置设为拆分位置
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
