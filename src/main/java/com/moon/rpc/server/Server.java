package com.moon.rpc.server;

import com.moon.rpc.NameService;
import com.moon.rpc.RpcAccessPoint;
import com.moon.rpc.demo.HelloService;
import com.moon.rpc.demo.HelloServiceImpl;
import com.moon.rpc.spi.ServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.File;
import java.net.URI;

/**
 * @author yujiangtao
 * @date 2021/2/2 下午10:09
 */
public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws Exception {
        startWithFileRegister();
        // startWithDatabaseRegister();
    }

    /**
     * 使用文件作为注册中心
     * @throws Exception
     */
    private static void startWithFileRegister() throws Exception {
        String serviceName = HelloService.class.getCanonicalName();
        File tmpDirFile = new File(System.getProperty("java.io.tmpdir"));
        File file = new File(tmpDirFile, "simple_rpc_name_service.data");
        HelloService helloService = new HelloServiceImpl();
        logger.info("创建并启动RpcAccessPoint...");
        try(RpcAccessPoint rpcAccessPoint = ServiceSupport.load(RpcAccessPoint.class);
            Closeable ignored = rpcAccessPoint.startServer()) {
            NameService nameService = rpcAccessPoint.getNameService(file.toURI());
            assert nameService != null;
            logger.info("向RpcAccessPoint注册{}服务...", serviceName);
            URI uri = rpcAccessPoint.addServiceProvider(helloService, HelloService.class);
            logger.info("服务名：{}，向NameService注册...", serviceName);
            nameService.registerService(serviceName, uri);
            logger.info("开始提供服务，按任何键退出.");
            // no inspection Result of method call ignored
            System.in.read();
            logger.info("Bye!");
        }
    }

    /**
     * 使用数据库作为注册中心
     */
    private static void startWithDatabaseRegister() throws Exception {
        String serviceName = HelloService.class.getCanonicalName();
        HelloService helloService = new HelloServiceImpl();
        logger.info("创建并启动RpcAccessPoint...");
        try(RpcAccessPoint rpcAccessPoint = ServiceSupport.load(RpcAccessPoint.class);
            Closeable ignored = rpcAccessPoint.startServer()) {
            NameService nameService = rpcAccessPoint.getNameService(URI.create("jdbc:mysql://127.0.0.1:3306/bookstore"));
            assert nameService != null;
            logger.info("向RpcAccessPoint注册{}服务...", serviceName);
            URI uri = rpcAccessPoint.addServiceProvider(helloService, HelloService.class);
            logger.info("服务名：{}，向NameService注册...", serviceName);
            nameService.registerService(serviceName, uri);
            logger.info("开始提供服务，按任何键退出.");
            // no inspection Result of method call ignored
            System.in.read();
            nameService.unregisterService(serviceName);
            logger.info("Bye!");
        }
    }
}
