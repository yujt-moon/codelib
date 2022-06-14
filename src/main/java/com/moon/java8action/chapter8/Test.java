package com.moon.java8action.chapter8;

import com.moon.java8action.chapter8.chain.HeaderTextProcessing;
import com.moon.java8action.chapter8.chain.ProcessingObject;
import com.moon.java8action.chapter8.chain.SpellCheckerProcessing;
import com.moon.java8action.chapter8.observer.Feed;
import com.moon.java8action.chapter8.observer.Guardian;
import com.moon.java8action.chapter8.observer.LeMonde;
import com.moon.java8action.chapter8.observer.NYTimes;
import com.moon.java8action.chapter8.strategy.IsAllLowerCase;
import com.moon.java8action.chapter8.strategy.IsNumeric;
import com.moon.java8action.chapter8.strategy.Validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) throws IOException {
        String oneLine = processFile((BufferedReader b) -> b.readLine());
        String twoLine = processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(oneLine);
        System.out.println(twoLine);
        System.out.println("============================");

        // 普通方式
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");
        System.out.printf("b1 = %s, b2 = %s\n", b1, b2);
        System.out.println("============================");

        // lambda 表达式
        Validator numericValidator2 = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b3 = numericValidator2.validate("aaaa");
        Validator lowerCaseValidator2 = new Validator((String s) -> s.matches("\\d+"));
        boolean b4 = lowerCaseValidator2.validate("bbbb");
        System.out.printf("b3 = %s, b4 = %s\n", b3, b4);
        System.out.println("============================");

        // 普通方式
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());
        feed.notifyObservers("The queen said her favourite book is Java 8 in Action!");
        System.out.println("=============================");

        // lambda
        Feed f = new Feed();
        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        });
        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet);
            }
        });
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
        System.out.println("============================");

        // 普通方法
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);
        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);
        System.out.println("==============================");

        // lambda
        UnaryOperator<String> headerProcessing =
                                        (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing =
                                        (String text) -> text.replaceAll("labda", "lambda");
        // 将两个方法结合起来，结果就是一个操作链
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        String result2 = pipeline.apply("Aren't  labdas really sexy?!!");
        System.out.println(result2);
        System.out.println("===========================");


        List<Integer> result3 = IntStream.rangeClosed(1, 5)
                                .boxed()
                                .peek(x -> System.out.println("from stream: " + x))
                                .map(x -> x + 17)
                                .peek(x -> System.out.println("after map: " + x))
                                .filter(x -> x % 2 == 0)
                                .peek(x -> System.out.println("after filter: " + x))
                                .limit(3)
                                .peek(x -> System.out.println("after limit: " + x))
                                .peek(x -> System.out.println("------------------"))
                                .collect(Collectors.toList());
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(
                new FileReader("/home/yujt/study/note/csapp/csapp.h"))) {
            return p.process(br);
        }
    }
}
