package com.moon.rpc;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;

/**
 * 注册中心
 *
 * @author yujiangtao
 * @date 2021/1/30 上午11:24
 */
public interface NameService {

    /**
     * 注册服务
     * @param serviceName 服务名称
     * @param uri 服务地址
     * @throws IOException
     */
    void registerService(String serviceName, URI uri) throws IOException;

    /**
     * 卸载服务
     * @param serviceName
     * @throws IOException
     */
    void unregisterService(String serviceName) throws IOException;

    /**
     * 查询服务地址
     * @param serviceName
     * @return
     * @throws IOException
     */
    URI lookupService(String serviceName) throws IOException;

    /**
     * 所有支持的协议
     * @return
     */
    Collection<String> supportedSchemes();

    /**
     * 连接注册中心
     * @param nameServiceUri
     */
    void connect(URI nameServiceUri);
}
