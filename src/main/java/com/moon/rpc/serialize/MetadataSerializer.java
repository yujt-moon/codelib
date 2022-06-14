package com.moon.rpc.serialize;

import com.moon.rpc.nameservice.Metadata;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Size of the map                      2 bytes
 *      Map entry:
 *          Key string:
 *              Length:                 2 bytes
 *              Serialized key bytes:   variable length
 *          Value list
 *              List size:              2 bytes
 *              item(URI):
 *                  Length:             2 bytes
 *                  serialized uri:     variable length
 *              item(URI):
 *              ...
 *      Map entry:
 *      ...
 *
 *
 * @author yujiangtao
 * @date 2021/1/30 下午12:40
 */
public class MetadataSerializer implements Serializer<Metadata> {
    @Override
    public int size(Metadata entity) {
        return Short.BYTES +                    // Size of the map      2 bytes
                entity.entrySet().stream()
                        .mapToInt(this::entrySize).sum();
    }

    /**
     * 获取 map 中一条数据的长度
     * @param e
     * @return
     */
    private int entrySize(Map.Entry<String, List<URI>> e) {
        // Map entry:
        return Short.BYTES +        // Key string length:       2 bytes
                e.getKey().getBytes().length +      // Serialized key bytes: variable length
                Short.BYTES + // List size:     2 bytes
                e.getValue().stream() // Value list
                        .mapToInt(uri -> {
                            return Short.BYTES +        // Key string length:   2 bytes
                                    uri.toASCIIString().getBytes(StandardCharsets.UTF_8).length; // Serialized key bytes:   variable length
                        }).sum();
    }

    @Override
    public void serialize(Metadata entity, byte[] bytes, int offset, int length) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, length);
        buffer.putShort(toShortSafely(entity.size()));

        entity.forEach((k, v) -> {
            byte[] keyBytes = k.getBytes(StandardCharsets.UTF_8);
            // 设置长度
            buffer.putShort(toShortSafely(keyBytes.length));
            buffer.put(keyBytes);

            // 设置 list 的长度
            buffer.putShort(toShortSafely(v.size()));
            for (URI uri : v) {
                byte[] uriBytes = uri.toASCIIString().getBytes(StandardCharsets.UTF_8);
                buffer.putShort(toShortSafely(uriBytes.length));
                buffer.put(uriBytes);
            }
        });
    }

    @Override
    public Metadata parse(byte[] bytes, int offset, int length) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, length);

        Metadata metadata = new Metadata();
        int sizeOfMap = buffer.getShort();
        for (int i = 0; i < sizeOfMap; i++) {
            int keyLength = buffer.getShort();
            byte[] keyBytes = new byte[keyLength];
            buffer.get(keyBytes);
            String key = new String(keyBytes, StandardCharsets.UTF_8);

            int uriListSize = buffer.getShort();
            List<URI> uriList = new ArrayList<>(uriListSize);
            for (int j = 0; j < uriListSize; j++) {
                int uriLength = buffer.getShort();
                byte[] uriBytes = new byte[uriLength];
                buffer.get(uriBytes);
                URI uri = URI.create(new String(uriBytes, StandardCharsets.UTF_8));
                uriList.add(uri);
            }
            metadata.put(key, uriList);
        }
        return metadata;
    }

    @Override
    public byte type() {
        return Types.TYPE_METADATA;
    }

    @Override
    public Class<Metadata> getSerializeClass() {
        return Metadata.class;
    }

    /**
     * 防止超出 short 的取值范围，正整数变成负整数
     * @param v
     * @return
     */
    private short toShortSafely(int v) {
        assert v < Short.MAX_VALUE;
        return (short) v;
    }
}
