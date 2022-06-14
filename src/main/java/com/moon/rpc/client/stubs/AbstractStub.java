package com.moon.rpc.client.stubs;

import com.moon.rpc.client.RequestIdSupport;
import com.moon.rpc.client.RpcRequest;
import com.moon.rpc.client.ServiceTypes;
import com.moon.rpc.serialize.SerializeSupport;
import com.moon.rpc.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午4:01
 */
public abstract class AbstractStub implements ServiceStub {

    protected Transport transport;

    protected final Logger logger = LoggerFactory.getLogger(AbstractStub.class);

    protected byte[] invokeRemote(RpcRequest request) {
        logger.info("远程方法调用开始...");
        Header header = new Header(RequestIdSupport.next(), 1, ServiceTypes.TYPE_RPC_REQUEST);
        byte[] payload = SerializeSupport.serialize(request);
        Command requestCommand = new Command(header, payload);
        try {
            Command responseCommand = transport.send(requestCommand).get();
            logger.info("返回结果...");
            ResponseHeader responseHeader = (ResponseHeader) responseCommand.getHeader();
            if(responseHeader.getCode() == Code.SUCCESS.getCode()) {
                return responseCommand.getPayload();
            } else {
                throw new Exception(responseHeader.getError());
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}
