package com.moon.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author yujiangtao
 * @date 2020/8/29 上午11:16
 */
public class ChangeVersionAdaptor extends ClassVisitor {

    public ChangeVersionAdaptor(ClassVisitor cv) {
        super(Opcodes.ASM4, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(Opcodes.V1_5, access, name, signature, superName, interfaces);
    }
}
