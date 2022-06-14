package com.moon.rpc.nameservice;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

/**
 * HashMap<String, List<URI>>
 *     String: 服务名
 *     List<URI>: 服务提供者 URI 列表
 *
 * @author yujiangtao
 * @date 2021/1/30 下午12:47
 */
public class Metadata extends HashMap<String, List<URI>> {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Metadata:").append("\n");
        for (Entry<String, List<URI>> entry : entrySet()) {
            sb.append("\t").append("Classname: ")
                    .append(entry.getKey()).append("\n");
            sb.append("\t").append("URIs:").append("\n");
            for (URI uri : entry.getValue()) {
                sb.append("\t\t").append(uri).append("\n");
            }
        }
        return sb.toString();
    }
}
