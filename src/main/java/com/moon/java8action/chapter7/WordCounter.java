package com.moon.java8action.chapter7;

/**
 * 用来在遍历 Character 流时技术的类
 */
public class WordCounter {

    private final int counter;

    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    /**
     * 和迭代算法一样，accumlulate 方法一个个遍历 Character
     * @param c
     * @return
     */
    public WordCounter accumulate(Character c) {
        if(Character.isWhitespace(c)) {
            return lastSpace ?
                    this :
                    new WordCounter(counter, true);
        } else {
            // 上一个字符是空格，而当前遍历的字符不是空格时，将单词计数器加一
            return lastSpace ?
                    new WordCounter(counter + 1, false) :
                    this;
        }
    }

    /**
     * 合并两个 WordCounter，把其计数器加起来
     * @param wordCounter
     * @return
     */
    public WordCounter combine(WordCounter wordCounter) {
        // 仅需要计数器的综合，无需关心 lastSpace
        return new WordCounter(counter + wordCounter.counter,
                wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}
