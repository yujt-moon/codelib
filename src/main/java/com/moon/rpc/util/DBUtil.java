package com.moon.rpc.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * database util
 *
 * @author yujiangtao
 * @date 2021/2/21 下午7:12
 */
public class DBUtil {

    private static Connection connection;

    /**
     * 获取数据库连接
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static Connection getConnection(String url, String username, String password) {
        Connection conn = null;
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while(iterator.hasNext()) {
            Driver driver = iterator.next();
        }
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = conn;
        return connection;
    }

    public static <T> List<T> query(String sql, String[] args, Class<T> clazz) {
        List<T> results = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            if(args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i+1, args[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                T t = clazz.newInstance();
                Field[] declaredFields = clazz.getDeclaredFields();
                for (int i = 0; i < declaredFields.length; i++) {
                    Field declaredField = declaredFields[i];
                    String name = declaredField.getName();
                    String columnName = fieldNameToColumnName(name);
                    Class<?> type = declaredField.getType();
                    Object columnVal = null;
                    if(type == String.class) {
                         columnVal = resultSet.getString(columnName);
                    } else if(type == int.class) {
                        columnVal = resultSet.getInt(columnName);
                    }
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnVal);
                }
                results.add(t);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static int execute(String sql, String[] args) {
        int affectedRow = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            if(args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i+1, args[i]);
                }
            }
            preparedStatement.execute();
            affectedRow = preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRow;
    }

    public static void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * 字段名转成数据库列名
     * @param fieldName
     * @return
     */
    public static String fieldNameToColumnName(String fieldName) {
        if(fieldName == null) {
            throw new IllegalArgumentException("字段名不能为空！");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < fieldName.length(); i++) {
            char character = fieldName.charAt(i);
            if(Character.isUpperCase(character)) {
                sb.append("_" + Character.toLowerCase(character));
            } else {
                sb.append(character);
            }
        }
        return sb.toString();
    }
}
