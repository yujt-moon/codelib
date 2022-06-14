package com.moon.asm;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author yujiangtao
 * @date 2020/8/28 上午10:53
 */
@Resource
public class Example implements Serializable, AutoCloseable {
    
    private int num;

    private String name;

    public static final String CONSTANT = "cafebabe";

    @Override
    public void close() throws Exception {

    }

    public static String getConstant() {
        return CONSTANT;
    }
}
