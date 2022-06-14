package com.moon.rpc.serialize;

import com.moon.rpc.client.RpcRequest;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author yujiangtao
 * @date 2021/2/4 下午1:33
 */
public class RpcRequestSerializer implements Serializer<RpcRequest> {
    @Override
    public int size(RpcRequest request) {
        return Integer.BYTES + request.getInterfaceName().getBytes(StandardCharsets.UTF_8).length +
                Integer.BYTES + request.getMethodName().getBytes(StandardCharsets.UTF_8).length +
                Integer.BYTES + request.getSerializedArguments().length;
    }

    @Override
    public void serialize(RpcRequest request, byte[] bytes, int offset, int length) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, length);
        byte[] tempBytes = request.getInterfaceName().getBytes(StandardCharsets.UTF_8);
        buffer.putInt(tempBytes.length);
        buffer.put(tempBytes);

        tempBytes = request.getMethodName().getBytes(StandardCharsets.UTF_8);
        buffer.putInt(tempBytes.length);
        buffer.put(tempBytes);

        tempBytes = request.getSerializedArguments();
        buffer.putInt(tempBytes.length);
        buffer.put(tempBytes);
    }

    @Override
    public RpcRequest parse(byte[] bytes, int offset, int length) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, length);
        int len = buffer.getInt();
        byte[] tmpBytes = new byte[len];
        buffer.get(tmpBytes);
        String interfaceName = new String(tmpBytes, StandardCharsets.UTF_8);

        len = buffer.getInt();
        tmpBytes = new byte[len];
        buffer.get(tmpBytes);
        String methodName = new String(tmpBytes, StandardCharsets.UTF_8);

        len = buffer.getInt();
        tmpBytes = new byte[len];
        buffer.get(tmpBytes);
        byte[] serializedArgs = tmpBytes;

        return new RpcRequest(interfaceName, methodName, serializedArgs);
    }

    @Override
    public byte type() {
        return Types.TYPE_RPC_REQUEST;
    }

    @Override
    public Class<RpcRequest> getSerializeClass() {
        return RpcRequest.class;
    }
}
