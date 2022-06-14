package com.moon.mybatis.util;

import com.moon.mybatis.log.Log;
import com.moon.mybatis.log.LogFactory;
import com.moon.util.StringUtils;

/**
 * @author yujiangtao
 * @date 2020/9/10 上午10:18
 */
public class ClassUtils {

    protected static Log log = LogFactory.getLog(ClassUtils.class);

    public static Class<?> resolveClass(String classStr) {
        if(StringUtils.isBlank(classStr)) {
            throw new IllegalArgumentException("类的全限定名不能为空！");
        }
        ClassLoader classLoader = ClassUtils.class.getClassLoader();
        if(classLoader != null) {
            try {
                return classLoader.loadClass(classStr);
            } catch (ClassNotFoundException e) {
                log.error("加载类 [" + classStr + "] 失败！", e);
                throw new RuntimeException("加载类 [" + classStr + "] 失败！");
            }
        }
        return null;
    }
}
