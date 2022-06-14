package com.moon.rpc.transport;

/**
 * @author yujiangtao
 * @date 2021/2/1 下午11:08
 */
public interface RequestHandler {

    /**
     * 处理请求
     * @param requestCommand 请求命令
     * @return 响应命令
     */
    Command handle(Command requestCommand);

    /**
     * 支持的请求类型
     * @return
     */
    int type();
}
