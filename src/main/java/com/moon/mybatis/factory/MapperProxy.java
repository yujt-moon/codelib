package com.moon.mybatis.factory;

import com.moon.mybatis.config.Configuration;
import com.moon.mybatis.config.MappedStatement;
import com.moon.mybatis.sqlsession.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yujiangtao
 * @date 2020/12/26 下午8:59
 */
public class MapperProxy<T> implements InvocationHandler {

    private Configuration configuration;

    public MapperProxy(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取接口中需要代理的方法名称
        Class intf = proxy.getClass();
        String[] methodNames = getIntfMethodNames(intf);
        for (String methodName : methodNames) {
            if(method.getName().equals(methodName)) {
                SqlSession sqlSession = configuration.getSqlSessionFactory().openSqlSession();
                // 获取代理对象的接口的全限定名
                String statementId = method.getDeclaringClass().getName() + "." + methodName;
                // 获取对象的返回类型
                Class<?> returnType = method.getReturnType();
                MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
                switch (mappedStatement.getSqlCommandType()) {
                    case SELECT:
                        return executeSelect(sqlSession, statementId, args, returnType);
                    case INSERT:
                        return executeInsert(sqlSession, statementId, args);
                    case UPDATE:
                        return executeUpdate(sqlSession, statementId, args);
                    case DELETE:
                        return executeDelete(sqlSession, statementId, args);
                    default:
                        throw new RuntimeException("不支持的xxx！");

                }
            }
        }
        return null;
    }

    /**
     * 获取接口中的方法名称
     * @param intf
     * @return
     */
    private String[] getIntfMethodNames(Class intf) {
        Method[] declaredMethods = intf.getDeclaredMethods();
        if(declaredMethods != null && declaredMethods.length > 0) {
            String[] methodNames = new String[declaredMethods.length - 3];
            int j = 0;
            for (int i = 0; i < declaredMethods.length; i++) {
                String methodName = declaredMethods[i].getName();
                if("equals".equals(methodName) || "toString".equals(methodName) || "hashCode".equals(methodName)) {
                    continue;
                }
                methodNames[j++] = methodName;
            }
            return methodNames;
        }
        return null;
    }

    private Object executeSelect(SqlSession sqlSession, String statementId, Object[] args, Class<?> returnType) {
        if(returnType.getName().equals("java.util.List")) {
            return sqlSession.selectList(statementId, args[0]);
        }
        return sqlSession.selectOne(statementId, args[0]);
    }

    private int executeInsert(SqlSession sqlSession, String statementId, Object[] args) {
        return sqlSession.insert(statementId, args[0]);
    }

    private int executeUpdate(SqlSession sqlSession, String statementId, Object[] args) {
        return sqlSession.update(statementId, args[0]);
    }

    private int executeDelete(SqlSession sqlSession, String statementId, Object[] args) {
        return sqlSession.delete(statementId, args[0]);
    }
}
