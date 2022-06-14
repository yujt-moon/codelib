package com.moon.rpc.serialize;


import com.moon.rpc.spi.ServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yujiangtao
 * @date 2021/1/30 上午11:55
 */
public class SerializeSupport {

    private static final Logger logger = LoggerFactory.getLogger(SerializeSupport.class);

    /**
     * Class<?> 序列化对象类型
     * Serializer<?> 序列化实现
     */
    private static Map<Class<?>, Serializer<?>> serializerMap = new HashMap<>();

    /**
     * Byte 序列化实现类型
     * Class<?> 序列化对象类型
     */
    private static Map<Byte, Class<?>> typeMap = new HashMap<>();

    static {
        for (Serializer serializer : ServiceSupport.loadAll(Serializer.class)) {
            registerType(serializer.type(), serializer.getSerializeClass(), serializer);
            logger.info("Found serializer, class: {}, type: {}.",
                    serializer.getSerializeClass().getCanonicalName(),
                    serializer.type());
        }
    }

    public static <E> E parse(byte[] buffer) {
        return parse(buffer, 0, buffer.length);
    }

    public static <E> byte[] serialize(E entry) {
        Serializer<E> serializer = (Serializer<E>) serializerMap.get(entry.getClass());
        if(serializer == null) {
            throw new SerializeException(String.format("Unknown entry class type: %s", entry.getClass().toString()));
        }
        byte[] bytes = new byte[serializer.size(entry)+1];
        bytes[0] = serializer.type();
        serializer.serialize(entry, bytes, 1, bytes.length - 1);
        return bytes;
    }

    private static <E> void registerType(byte type, Class<E> eClass, Serializer<E> serializer) {
        serializerMap.put(eClass, serializer);
        typeMap.put(type, eClass);
    }

    private static byte parseEntryType(byte[] buffer) {
        return buffer[0];
    }

    private static <E> E parse(byte[] buffer, int offset, int length, Class<E> eClass) {
        Object entry = serializerMap.get(eClass).parse(buffer, offset, length);
        if(eClass.isAssignableFrom(entry.getClass())) {
            return (E) entry;
        } else {
            throw new SerializeException("Type mismatch");
        }
    }

    private static <E> E parse(byte[] buffer, int offset, int length) {
        byte type = parseEntryType(buffer);
        Class<E> eClass = (Class<E>)typeMap.get(type);
        if(null == eClass) {
            throw new SerializeException(String.format("Unknown entry type: %d!", type));
        } else {
            return parse(buffer, offset + 1, length - 1, eClass);
        }
    }
}
