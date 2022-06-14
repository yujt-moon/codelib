package com.moon.rpc.transport;

import com.moon.rpc.spi.ServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yujiangtao
 * @date 2021/2/1 下午8:29
 */
public class RequestHandlerRegistry {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerRegistry.class);

    private Map<Integer, RequestHandler> handlerMap = new HashMap<>();

    private static RequestHandlerRegistry instance = null;

    public static RequestHandlerRegistry getInstance() {
        if(null == instance) {
            instance = new RequestHandlerRegistry();
        }
        return instance;
    }

    private RequestHandlerRegistry() {
        Collection<RequestHandler> requestHandlers = ServiceSupport.loadAll(RequestHandler.class);
        for (RequestHandler requestHandler : requestHandlers) {
            handlerMap.put(requestHandler.type(), requestHandler);
            logger.info("Load request handler, type: {}, class: {}.", requestHandler.type(),
                    requestHandler.getClass().getCanonicalName());
        }
    }

    public RequestHandler get(int type) {
        return handlerMap.get(type);
    }
}
