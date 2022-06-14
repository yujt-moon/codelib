package com.moon.rpc.client.stubs;

import com.moon.rpc.transport.Transport;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午3:59
 */
public interface ServiceStub {
    void setTransport(Transport transport);
}
