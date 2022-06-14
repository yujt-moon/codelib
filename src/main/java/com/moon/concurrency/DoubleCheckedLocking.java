package com.moon.concurrency;

/**
 * Resource 在未初始化时，逸出
 *
 * @author yujiangtaos
 * @date 2021/3/7 下午11:48
 */
@NotThreadSafe
public class DoubleCheckedLocking {

    private static Resource resource;

    public static Resource getInstance() {
        if(resource == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null) {
                    resource = new Resource();
                }
            }
        }
        return resource;
    }

    private static class Resource {

    }
}
