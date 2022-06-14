package com.moon.asm.baeldung;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.IOException;

/**
 * @author yujiangtao
 * @date 2020/8/28 下午5:24
 */
public class CustomClassWriter {

    static String className = "com.moon.asm.baeldung.Demo";
    static String cloneableInterface = "java/lang/Clonaeable";
    ClassReader reader;
    ClassWriter writer;

    public CustomClassWriter() throws IOException {
        reader = new ClassReader(className);
        writer = new ClassWriter(reader, 0);
    }

    public byte[] addField() {
        AddFieldAdapter addFieldAdapter = new AddFieldAdapter("aNewBooleanField", Opcodes.ACC_PUBLIC, "I", writer);
        reader.accept(addFieldAdapter, 0);
        return writer.toByteArray();
    }

    public byte[] publicizeMethod() {
        PublicizeMethodAdapter publicizeMethodAdapter = new PublicizeMethodAdapter(writer);
        reader.accept(publicizeMethodAdapter, 0);
        return writer.toByteArray();
    }

    public byte[] addInterface() {
        AddInterfaceAdapter addInterfaceAdapter = new AddInterfaceAdapter(writer, cloneableInterface);
        reader.accept(addInterfaceAdapter, 0);
        return  writer.toByteArray();
    }
}
