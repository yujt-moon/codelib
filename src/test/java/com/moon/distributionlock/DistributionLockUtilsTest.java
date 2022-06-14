package com.moon.distributionlock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 分布式锁工具测试类
 * @author yujiangtao
 * @date 2019/4/26 17:04
 */
public class DistributionLockUtilsTest {
    Jedis jedis = null;

    @Before
    public void before() {
        jedis = new Jedis("192.168.137.200", 6379);
    }

    @Test
    public void testTryGetDistributionLock() {
        boolean flag = DistributionLockUtils.tryGetDistributedLock(jedis, "hehe", "001", 10000);
        assertTrue(flag);
    }

    @Test
    public void testReleaseDistributionLock() {
        boolean flag = DistributionLockUtils.releaseDistributionLock(jedis, "hehe", "002");
        assertFalse(flag);

        DistributionLockUtils.tryGetDistributedLock(jedis, "hehe", "003", 10000);
        boolean flag2 = DistributionLockUtils.releaseDistributionLock(jedis, "hehe", "003");
        assertTrue(flag2);

    }

    @After
    public void after() {
        jedis.close();
    }
}
