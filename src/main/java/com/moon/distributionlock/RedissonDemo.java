package com.moon.distributionlock;

import org.junit.BeforeClass;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

/**
 * @author yujiangtao
 * @date 2021/3/30 下午11:04
 */
public class RedissonDemo {

    @BeforeClass
    public void init() {
        // 1. create config object
        Config config = new Config();
        config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("redis://127.0.0.1:6379");

        // or read config from file
        // config = Config.fromYAML(new File("config-file.yaml"));

        // 2. Create Redission instance

        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);

        // Reactive API
        RedissonReactiveClient redissonReactive = redisson.reactive();

        // RxJava3 API
        RedissonRxClient redissonRx = redisson.rxJava();

        // 3. Get Redis based implementation of java.util.concurrent.ConcurrentMap
        RMap<String, String> map = redisson.getMap("myMap");

        RMapReactive<String, String> mapReactive = redissonReactive.getMap("myMap");

        RMapRx<String, String> mapRx = redissonRx.getMap("myMap");

        // 4. Get Redis based implementation of java.util.concurrent.locks.Lock
        RLock lock = redisson.getLock("myLock");

        RLockReactive lockReactive = redissonReactive.getLock("myLock");

        RLockRx lockRx = redissonRx.getLock("myLock");

        // 4. Get Redis based implement of java.util.concurrent.ExecutorService
        RExecutorService executor = redisson.getExecutorService("myExecutorService");
    }

    public void concurrentMap() {

    }

}
