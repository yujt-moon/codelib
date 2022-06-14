package com.moon.distributionlock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author yujiangtao
 * @date 2018/6/26 14:46
 */
public class DistributionLockUtils {

    public static final String LOCK_SUCCESS = "OK";

    public static final String SET_IF_NOT_EXIST = "NX";

    public static final String SET_WITH_EXPIRE_TIME = "PX";

    public static final Long RELEASE_SUCCESS = 1L;

    private Jedis jedis = new Jedis();

    /**
     * 尝试获取分布式锁
     * @param jedis
     * @param lockKey 锁的键
     * @param requestId 锁的值
     * @param expireTime 超时时间（单位：毫秒）
     * @return
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if(LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 释放分布式锁
     * @param jedis
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return
     */
    public static boolean releaseDistributionLock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if(RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
