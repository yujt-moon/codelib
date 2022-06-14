package com.moon.util;

import com.moon.rpc.RegisterCenter;
import com.moon.rpc.util.DBUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2021/2/21 下午10:20
 */
public class DBUtilTest {

    @Test
    public void testQuery() {
        DBUtil.getConnection("jdbc:mysql://127.0.0.1:3306/bookstore",
                "root", "root");
        int rows = DBUtil.execute("insert into register_center (service_name, service_url) values (?, ?)",
                new String[]{"zzz", "zzzz"});
        System.out.println(rows);
        List<RegisterCenter> registerCenters = DBUtil.query("select * from register_center",
                null, RegisterCenter.class);
        registerCenters.forEach(System.out::println);
        DBUtil.closeConnection();
    }
}
