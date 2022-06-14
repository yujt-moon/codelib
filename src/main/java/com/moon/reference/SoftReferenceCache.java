package com.moon.reference;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用软引用，作为缓存
 *
 * @author yujiangtao
 * @date 2020/11/4 上午11:29
 */
public class SoftReferenceCache<K, V> {

    private final Map<K, SoftReference<V>> cache;

    public SoftReferenceCache() {
        cache = new HashMap<>();
    }

    public void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
    }

    public V get(K key) {
        SoftReference<V> vSoftReference = cache.get(key);
        if(vSoftReference != null) {
            return vSoftReference.get();
        }
        return null;
    }
}
