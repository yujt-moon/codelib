package com.moon.rpc.server;

/**
 * @author yujiangtao
 * @date 2021/2/2 上午9:46
 */
public interface ServiceProviderRegistry {
    <T> void addServiceProvider(Class<? extends T> serviceClass, T serviceProvider);
}
