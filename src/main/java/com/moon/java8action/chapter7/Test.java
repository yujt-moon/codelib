package com.moon.java8action.chapter7;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Test {

    static final String SENTENCE = " Nel   mezzo del cammin di nostra vita " +
            "mi ritrovai in una selva oscura" +
            " ché la dritta via era   smarrita ";

    public static void main(String[] args) {
        System.out.println("Sequential sum done in: " +
                Bench.measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs");
        System.out.println("==============================");

        System.out.println("Iterative sum done in: " +
                Bench.measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");
        System.out.println("=============================");

        System.out.println("Parallel sum done in: " +
                Bench.measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + " msecs");
        System.out.println("=============================");

        System.out.println("Ranged sum done in: " +
                Bench.measureSumPerf(ParallelStreams::rangedSum, 10_000_000) + " msecs");
        System.out.println("=============================");

        System.out.println("Parallel range sum done in: " +
                Bench.measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000) + " msecs");
        System.out.println("=============================");

        System.out.println("Side effect sum done in: " +
                Bench.measureSumPerf(ParallelStreams::sideEffectSum, 10_000_000) + " msecs");
        System.out.println("=============================");

        System.out.println("SideEffect parallel sum done in: " +
                Bench.measureSumPerf(ParallelStreams::sideEffectParallelSum, 10_000_000L) + " msecs");
        System.out.println("=============================");

        System.out.println("ForkJoin sum done in: " +
                Bench.measureSumPerf(ParallelStreams::forkJoinSum, 10_000_000L) + " msecs");
        System.out.println("=============================");

        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
        System.out.println("=============================");

        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(stream) + " words");
        System.out.println("=============================");

        // 让 WordCounter 并行工作
        /*System.out.println("Parallel found " + countWords(stream.parallel()) + " words");
        System.out.println("=============================");*/

        WordCounterSpliterator spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream1 = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream1) + " words");
    }


    /**
     * 一个迭代式字数统计方法
     * @param s
     * @return
     */
    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if(Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if(lastSpace)
                    counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }
}
