package com.moon.rpc.serialize;

/**
 * @author yujiangtao
 * @date 2021/2/1 下午7:29
 */
public class SerializeException extends RuntimeException {

    public SerializeException(String msg) {
        super(msg);
    }

    public SerializeException(Throwable cause) {
        super(cause);
    }
}
