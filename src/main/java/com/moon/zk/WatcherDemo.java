package com.moon.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author yujiangtao
 * @date 2021/3/17 下午9:27
 */
public class WatcherDemo implements Watcher {

    static ZooKeeper zooKeeper;

    static {
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 4000, new WatcherDemo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("eventType: " + watchedEvent.getType());
        if(watchedEvent.getType() == Event.EventType.NodeDataChanged) {
            try {
                zooKeeper.exists(watchedEvent.getPath(), true);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
            throws IOException, KeeperException, InterruptedException {
        String path = "/watcher";
        if(zooKeeper.exists(path, false) == null) {
            zooKeeper.create("/watcher", "0".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        Thread.sleep(1000);
        System.out.println("--------------------");
        // true 表示使用 zookeeper 实例总配置的 watcher
        Stat stat = zooKeeper.exists(path, true);
        System.in.read();
    }
}
