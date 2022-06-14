package com.moon.rpc.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yujiangtao
 * @date 2021/1/30 上午11:36
 */
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(String name) {
        logger.info("HelloServiceImpl 收到：{}.", name);
        String ret = "Hello, " + name;
        logger.info("HelloServiceImpl 返回：{}.", ret);
        return ret;
    }
}
