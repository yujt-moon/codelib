package com.moon.rpc.serialize;

/**
 * 序列化接口
 *
 * @author yujiangtao
 * @date 2021/1/30 下午12:01
 */
public interface Serializer<T> {

    /**
     * 计算对象序列化后的长度，主要用于申请存放序列化数据的字节数组
     * @param entity 代序列化的对象
     * @return 对象序列化后的长度
     */
    int size(T entity);

    /**
     * 序列化对象。将给定的对象序列化成字节数组
     * @param entity 待序列化的对象
     * @param bytes 存放序列化数据的字节数组
     * @param offset 数组的偏移量，从这个位置开始写入序列化数据
     * @param length 对象序列化后的长度，也就是 {@link Serializer#size(Object)} 方法
     */
    void serialize(T entity, byte[] bytes, int offset, int length);

    /**
     * 反序列化对象，将字节数组还原成对象
     * @param bytes 存放序列化数据的字节数组
     * @param offset 数组的偏移量，从这个位置开始读入序列化数据
     * @param length 对象序列化后的长度
     * @return 反序列化之后生成的对象
     */
    T parse(byte[] bytes, int offset, int length);

    /**
     * 用一个字节标识对象类型，每种类型的数据应该具有不同的类型值
     * @return
     */
    byte type();

    /**
     * 返回序列化对象类型的 Class 对象。
     * @return
     */
    Class<T> getSerializeClass();
}
