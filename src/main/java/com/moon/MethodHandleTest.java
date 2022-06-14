package com.moon;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author yujiangtao
 * @date 2021/7/27 下午9:05
 */
public class MethodHandleTest {

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType mt = MethodType.methodType(String.class, char.class, char.class);
        MethodHandle mh = lookup.findVirtual(String.class, "replace", mt);
        String s = (String) mh.invokeExact("daddy", 'd', 'n');
        System.out.println(s);
    }
}
