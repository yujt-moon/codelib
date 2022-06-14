package com.moon.mybatis;

import com.moon.mybatis.mapperproxy.MapperProxyTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author yujiangtao
 * @date 2020/9/9 上午11:51
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ReadConfigTest.class,
        BookDaoTest.class,
        MapperProxyTest.class
})
public class AllTest {
}
