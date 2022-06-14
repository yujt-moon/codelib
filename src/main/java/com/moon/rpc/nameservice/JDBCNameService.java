package com.moon.rpc.nameservice;

import com.moon.rpc.NameService;
import com.moon.rpc.RegisterCenter;
import com.moon.rpc.util.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author yujiangtao
 * @date 2021/2/20 下午10:37
 */
public class JDBCNameService implements NameService {

    private static final Logger logger = LoggerFactory.getLogger(JDBCNameService.class);

    private static final Collection<String> schemes = Collections.singleton("jdbc");

    private static String url;

    @Override
    public synchronized void registerService(String serviceName, URI uri) throws IOException {
        DBUtil.getConnection(url, "root", "root");
        DBUtil.execute("insert into register_center (service_name, service_url) values (?, ?)",
                new String[]{serviceName, uri.toASCIIString()});
    }

    @Override
    public void unregisterService(String serviceName) throws IOException {
        DBUtil.getConnection(url, "root", "root");
        DBUtil.execute("delete from register_center where service_name = ?", new String[]{serviceName});
    }

    @Override
    public URI lookupService(String serviceName) throws IOException {
        DBUtil.getConnection(url, "root", "root");
        List<RegisterCenter> registerCenters = DBUtil.query("select * from register_center where service_name = ?",
                new String[]{serviceName}, RegisterCenter.class);
        if(registerCenters == null || registerCenters.size() == 0) {
            throw new RuntimeException("无法找到对应的服务！");
        }
        return URI.create(registerCenters.get(0).getServiceUrl());
    }

    @Override
    public Collection<String> supportedSchemes() {
        return schemes;
    }

    @Override
    public void connect(URI nameServiceUri) {
        if(schemes.contains(nameServiceUri.getScheme())) {
            url = nameServiceUri.toASCIIString();
        } else {
            throw new RuntimeException("Unsupported scheme!");
        }
    }
}
