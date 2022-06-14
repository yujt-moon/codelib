package com.moon.rpc.factory;

import com.itranswarp.compiler.JavaStringCompiler;
import com.moon.rpc.client.stubs.ServiceStub;
import com.moon.rpc.transport.Transport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午3:54
 */
public class DynamicStubFactory implements StubFactory {

    private final Logger logger = LoggerFactory.getLogger(DynamicStubFactory.class);

    private final static String STUB_SOURCE_TEMPLATE =
            "package com.moon.rpc.client.stubs;\n\n" +
            "import com.moon.rpc.serialize.SerializeSupport;\n" +
            "import com.moon.rpc.client.RpcRequest;\n" +
            "\n" +
            "public class %s extends AbstractStub implements %s {\n" +
            "   @Override\n" +
            "   public String %s(String arg) {\n" +
            "       return SerializeSupport.parse(\n" +
            "               invokeRemote(\n" +
            "                   new RpcRequest(\n" +
            "                       \"%s\",\n" +
            "                       \"%s\",\n" +
            "                       SerializeSupport.serialize(arg)\n" +
            "                   )\n" +
            "               )\n" +
            "       );\n" +
            "   }\n" +
            "}";

    @Override
    public <T> T createStub(Transport transport, Class<T> serviceClass) {
        try {
            String stubSimpleName = serviceClass.getSimpleName() + "Stub";
            String classFullName = serviceClass.getName();
            String stubFullName = "com.moon.rpc.client.stubs." + stubSimpleName;
            String methodName = serviceClass.getMethods()[0].getName();

            String source = String.format(STUB_SOURCE_TEMPLATE, stubSimpleName, classFullName,
                    methodName, classFullName, methodName);
            logger.info("源代码为：\n" + source);
            // 编译源代码
            JavaStringCompiler compiler = new JavaStringCompiler();
            Map<String, byte[]> results = compiler.compile(stubSimpleName + ".java", source);
            // 加载编译好的类
            Class<?> clazz = compiler.loadClass(stubFullName, results);
            // 把 Transport 赋值给桩
            ServiceStub stubInstance = (ServiceStub) clazz.newInstance();
            stubInstance.setTransport(transport);
            // 返回这个桩
            return (T) stubInstance;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
