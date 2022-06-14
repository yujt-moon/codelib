package com.moon.rpc.client;

import com.moon.rpc.NameService;
import com.moon.rpc.RpcAccessPoint;
import com.moon.rpc.demo.HelloService;
import com.moon.rpc.spi.ServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * @author yujiangtao
 * @date 2021/2/2 下午12:44
 */
public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws IOException {
        connectToFileRegister();
        // connectToDatabaseRegister();
    }

    /**
     * 连接到文件注册中心
     * @throws IOException
     */
    private static void connectToFileRegister() throws IOException {
        String serviceName = HelloService.class.getCanonicalName();
        File tempDirFile = new File(System.getProperty("java.io.tmpdir"));
        File file = new File(tempDirFile, "simple_rpc_name_service.data");
        String name = "Master MQ";
        try(RpcAccessPoint rpcAccessPoint = ServiceSupport.load(RpcAccessPoint.class)) {
            NameService nameService = rpcAccessPoint.getNameService(file.toURI());
            assert nameService != null;
            URI uri = nameService.lookupService(serviceName);
            assert uri != null;
            logger.info("找到服务 {}, 提供者: {}.", serviceName, uri);
            HelloService helloService = rpcAccessPoint.getRemoteService(uri, HelloService.class);
            logger.info("请求服务, name: {}...", name);
            String response = helloService.hello(name);
            logger.info("收到响应: {}.", response);
        }
    }

    /**
     * 连接到数据库注册中心
     */
    private static void connectToDatabaseRegister() throws IOException {
        String serviceName = HelloService.class.getCanonicalName();
        String name = "Master MQ";
        try(RpcAccessPoint rpcAccessPoint = ServiceSupport.load(RpcAccessPoint.class)) {
            NameService nameService = rpcAccessPoint.getNameService(URI.create("jdbc:mysql://127.0.0.1:3306/bookstore"));
            assert nameService != null;
            URI uri = nameService.lookupService(serviceName);
            assert uri != null;
            logger.info("找到服务 {}, 提供者: {}.", serviceName, uri);
            HelloService helloService = rpcAccessPoint.getRemoteService(uri, HelloService.class);
            logger.info("请求服务, name: {}...", name);
            String response = helloService.hello(name);
            logger.info("收到响应: {}.", response);
        }
    }
}
