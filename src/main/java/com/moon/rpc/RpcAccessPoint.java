package com.moon.rpc;

import com.moon.rpc.spi.ServiceSupport;

import java.io.Closeable;
import java.net.URI;
import java.util.Collection;

/**
 * RPC 框架对外提供的服务接口
 *
 * @author yujiangtao
 * @date 2021/1/30 上午11:16
 */
public interface RpcAccessPoint extends Closeable {

    /**
     * 客户端获取远程服务的引用
     * @param uri 远程服务地址
     * @param serviceClass 服务的接口类的 Class
     * @param <T> 服务接口的类型
     * @return 远程服务的引用
     */
    <T> T getRemoteService(URI uri, Class<T> serviceClass);

    /**
     * 服务端注册服务的实现实例
     * @param service 实现实例
     * @param serviceClass 服务的接口类的 Class
     * @param <T> 服务接口的类型
     * @return 服务地址
     */
    <T> URI addServiceProvider(T service, Class<T> serviceClass);

    /**
     * 服务器端启动 RPC 框架，监听接口，开始提供远程服务。
     * @return 服务实例，用于程序停止的时候安全关闭服务。
     * @throws Exception
     */
    Closeable startServer() throws Exception;

    /**
     * 获取注册中心的引用
     * @param nameServiceUri
     * @return
     */
    default NameService getNameService(URI nameServiceUri) {
        Collection<NameService> nameServices = ServiceSupport.loadAll(NameService.class);
        for (NameService nameService : nameServices) {
            if(nameService.supportedSchemes().contains(nameServiceUri.getScheme())) {
                nameService.connect(nameServiceUri);
                return nameService;
            }
        }
        return null;
    }
}
