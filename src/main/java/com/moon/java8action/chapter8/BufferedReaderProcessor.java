package com.moon.java8action.chapter8;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderProcessor {

    /**
     * 使用 Lambda 表达式的函数接口，该接口能够抛出一个 IOException
     * @param b
     * @return
     * @throws IOException
     */
    String process(BufferedReader b) throws IOException;
}
