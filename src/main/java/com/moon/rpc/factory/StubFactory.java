package com.moon.rpc.factory;

import com.moon.rpc.transport.Transport;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午3:49
 */
public interface StubFactory {

    <T> T createStub(Transport transport, Class<T> serviceClass);
}
