package com.moon.asm.baeldung;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author yujiangtao
 * @date 2020/8/28 下午7:58
 */
public class AddInterfaceAdapter extends ClassVisitor {

    private String cloneableInterface;

    public AddInterfaceAdapter(ClassVisitor cv, String cloneableInterface) {
        super(Opcodes.ASM4, cv);
        this.cloneableInterface = cloneableInterface;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        String[] holding = new String[interfaces.length + 1];
        holding[holding.length - 1] = cloneableInterface;
        System.arraycopy(interfaces, 0, holding, 0, interfaces.length);
        cv.visit(Opcodes.V1_8, access, name, signature, superName, holding);
    }
}
