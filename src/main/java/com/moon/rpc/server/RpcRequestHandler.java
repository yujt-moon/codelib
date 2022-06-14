package com.moon.rpc.server;

import com.moon.rpc.client.RpcRequest;
import com.moon.rpc.client.ServiceTypes;
import com.moon.rpc.serialize.SerializeSupport;
import com.moon.rpc.spi.Singleton;
import com.moon.rpc.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yujiangtao
 * @date 2021/2/2 上午9:47
 */
@Singleton
public class RpcRequestHandler implements RequestHandler, ServiceProviderRegistry {

    private static final Logger logger = LoggerFactory.getLogger(RpcRequestHandler.class);

    /**
     * String: service name
     * Object: service provider
     */
    private Map<String, Object> serviceProviders = new HashMap<>();

    @Override
    public synchronized <T> void addServiceProvider(Class<? extends T> serviceClass, T serviceProvider) {
        serviceProviders.put(serviceClass.getCanonicalName(), serviceProvider);
        logger.info("Add service: {}, provider: {}.", serviceClass.getCanonicalName(),
                serviceProvider.getClass().getCanonicalName());
    }

    @Override
    public Command handle(Command requestCommand) {
        Header header = requestCommand.getHeader();
        // 从 payload 中反序列化 RpcRequest
        RpcRequest rpcRequest = SerializeSupport.parse(requestCommand.getPayload());
        try {
            // 查找所有已注册的服务提供方，寻找 rpcRequest 中需要的服务
            Object serviceProvider = serviceProviders.get(rpcRequest.getInterfaceName());
            if(serviceProvider != null) {
                // 找到服务提供者，利用 Java 反射机制调用服务的对应方法
                String arg = SerializeSupport.parse(rpcRequest.getSerializedArguments());
                Method method = serviceProvider.getClass().getMethod(rpcRequest.getMethodName(), String.class);
                String result = (String) method.invoke(serviceProvider, arg);
                // 把结果封装撑响应命令并返回
                return new Command(new ResponseHeader(type(), header.getVersion(), header.getRequestId()),
                        SerializeSupport.serialize(result));
            }
            // 如果没有找到， 返回 NO_PROVIDER 错误响应
            logger.warn("No service Provider of {}#{}(String)!", rpcRequest.getInterfaceName(), rpcRequest.getMethodName());
            return new Command(new ResponseHeader(type(), header.getVersion(), header.getRequestId(),
                    Code.NO_PROVIDER.getCode(), "No provider!"), new byte[0]);
        } catch (Throwable t) {
            // 发生异常，返回 UNKNOWN_ERROR 错误响应
            logger.warn("Exception: ", t);
            return new Command(new ResponseHeader(type(), header.getVersion(), header.getRequestId(),
                    Code.UNKNOWN_ERROR.getCode(), t.getMessage()), new byte[0]);
        }
    }

    @Override
    public int type() {
        return ServiceTypes.TYPE_RPC_REQUEST;
    }
}
