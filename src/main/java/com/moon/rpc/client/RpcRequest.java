package com.moon.rpc.client;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午4:03
 */
public class RpcRequest {

    private final String interfaceName;

    private final String methodName;

    private final byte[] serializedArguments;

    public RpcRequest(String interfaceName, String methodName, byte[] serializedArguments) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.serializedArguments = serializedArguments;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public byte[] getSerializedArguments() {
        return serializedArguments;
    }
}
