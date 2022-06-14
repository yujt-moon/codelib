package com.moon.mybatis.util;

import java.util.Collection;

/**
 * 集合工具类
 *
 * @author yujiangtao
 * @date 2020/8/24 下午3:20
 */
public class CollectionUtils {

    /**
     * 集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        if(collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 集合是否不为空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection collection) {
        if(collection == null || collection.size() == 0) {
            return false;
        }
        return true;
    }
}
