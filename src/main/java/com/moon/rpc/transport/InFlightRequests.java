package com.moon.rpc.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午3:03
 */
public class InFlightRequests implements Closeable {

    private final static long TIMEOUT_SEC = 10L;

    private final Semaphore semaphore = new Semaphore(10);

    private final Map<Integer, ResponseFuture> futureMap = new ConcurrentHashMap<>();

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private static final Logger logger = LoggerFactory.getLogger(InFlightRequests.class);

    private final ScheduledFuture scheduledFuture;

    public InFlightRequests() {
        this.scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(this::removeTimeoutFutures, TIMEOUT_SEC,
                TIMEOUT_SEC, TimeUnit.SECONDS);
    }

    private void removeTimeoutFutures() {
        /*futureMap.entrySet().removeIf(entry -> {
            if(System.nanoTime() - entry.getValue().getTimestamp() > TIMEOUT_SEC * 1_000_000_000L) {
                // 记录日志
                logger.info("requestId 为 {} 请求已超时..., 已从任务移除。", entry.getKey());
                semaphore.release();
                return true;
            } else {
                return false;
            }
        });*/
    }

    public void put(ResponseFuture responseFuture) throws InterruptedException, TimeoutException {
        if(semaphore.tryAcquire(TIMEOUT_SEC, TimeUnit.SECONDS)) {
            futureMap.put(responseFuture.getRequestId(), responseFuture);
        } else {
            throw new TimeoutException();
        }
    }

    public ResponseFuture remove(int requestId) {
        ResponseFuture future = futureMap.remove(requestId);
        if(null != future) {
            semaphore.release();
        }
        return future;
    }

    @Override
    public void close() throws IOException {
        scheduledFuture.cancel(true);
        scheduledExecutorService.shutdown();
    }
}
