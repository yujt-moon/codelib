package com.moon.design.proxy.cglibproxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author yujiangtao
 * @date 2020/8/18 下午5:41
 */
public class Client {
    public static void main(String[] args) {

        // 在指定目录生成动态代理类
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,
                System.getProperty("user.dir") + "/src/main/java/");

        // 创建 Enhancer 对象
        Enhancer enhancer = new Enhancer();
        // 设置目标类的字节码文件
        enhancer.setSuperclass(Actor.class);
        // 设置回调函数
        enhancer.setCallback(new LogMethodInterceptor());

        // 正式创建代理类
        Actor actor = (Actor) enhancer.create();

        System.out.println(actor.getWhat("haha"));

        System.out.println(actor.getRealName());
    }
}
