package com.moon.rpc.serialize;

import java.nio.charset.StandardCharsets;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午12:31
 */
public class StringSerializer implements Serializer<String> {

    @Override
    public int size(String entity) {
        return entity.getBytes(StandardCharsets.UTF_8).length;
    }

    @Override
    public void serialize(String entity, byte[] bytes, int offset, int length) {
        byte[] strBytes = entity.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(strBytes, 0, bytes, offset, strBytes.length);
    }

    @Override
    public String parse(byte[] bytes, int offset, int length) {
        return new String(bytes, offset, length, StandardCharsets.UTF_8);
    }

    @Override
    public byte type() {
        return Types.TYPE_STRING;
    }

    @Override
    public Class<String> getSerializeClass() {
        return String.class;
    }
}
