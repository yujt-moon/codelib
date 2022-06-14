package com.moon.asm.baeldung;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

/**
 * @author yujiangtao
 * @date 2020/8/28 下午7:24
 */
public class PublicizeMethodAdapter extends ClassVisitor {

    private PrintWriter pw = new PrintWriter(System.out);

    private TraceClassVisitor tracer;

    public PublicizeMethodAdapter(ClassVisitor cv) {
        super(Opcodes.ASM4, cv);
        this.cv = cv;
        tracer = new TraceClassVisitor(cv, pw);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if(name.equals("toUnsignedString0")) {
            System.out.println("Visiting unsigned method");
            return tracer.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, name, desc, signature, exceptions);
        }
        return tracer.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        tracer.visitEnd();
        System.out.println(tracer.p.getText());
    }
}
