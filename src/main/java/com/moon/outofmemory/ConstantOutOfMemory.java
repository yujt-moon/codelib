package com.moon.outofmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Described：常量池内存溢出探究
 * @VM args : -XX:PermSize=10M -XX:MaxPermSize=10M
 * @VM args 1.8 -Xms10M -Xmx10M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/yujt/logs/
 * @author YHJ create at 2011-10-30 下午04:28:30
 * @FileNmae com.yhj.jvm.memory.constant.ConstantOutOfMemory.java
 */

public class ConstantOutOfMemory {

    /**
     * @param args
     * @throws Exception
     * @Author YHJ create at 2011-10-30 下午04:28:25
     */
    public static void main(String[] args) throws Exception {

        try {

            List<String> strings = new ArrayList<String>();

            int i = 0;

            while(true){
                strings.add(String.valueOf(i++).intern());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
