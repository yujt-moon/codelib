package com.moon.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author yujiangtao
 * @date 2020/8/29 下午2:45
 */
public class MultiClassAdapter extends ClassVisitor {

    protected ClassVisitor[] cvs;

    public MultiClassAdapter(ClassVisitor[] cvs) {
        super(Opcodes.ASM4);
        this.cvs = cvs;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        for (ClassVisitor cv : cvs) {
            cv.visit(version, access, name, signature, superName, interfaces);
        }
    }
}
