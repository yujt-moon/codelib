package com.moon.design.proxy.jdkproxy;

/**
 * @author yujiangtao
 * @date 2020/8/17 下午8:53
 */
public class Client {
    public static void main(String[] args) throws Exception {

        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        SimpleCalculator simpleCalculator = new SimpleCalculator();
        Calculator calculator = new JDKDynamicProxy(simpleCalculator).getProxyInstance();
        int calculate = calculator.calculate(2, 3);
        System.out.println(calculate);

        ComplexCalculator complexCalculator = new ComplexCalculator();
        Calculator calculator2 = new JDKDynamicProxy(complexCalculator).getProxyInstance();
        int result = calculator2.calculate(2, 3);
    }
}
