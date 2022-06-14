package com.moon.util;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * 对象转map测试类
 * @author yujiangtao
 * @date 2019/1/28 10:22
 */
public class Object2MapUtilTest {

    /**
     * 测试对象转map
     * @throws IllegalAccessException
     */
    @Test
    public void testObjectToMap() throws IllegalAccessException {
        User user = new User("yu", "123");
        Map<String, Object> stringObjectMap = Object2MapUtil.objectToMap(user);
        assertEquals(stringObjectMap.get("username"), "yu");
        assertEquals(stringObjectMap.get("password"), "123");
    }

}

class User {
    private String username;

    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
