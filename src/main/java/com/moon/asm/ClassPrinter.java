package com.moon.asm;

import org.objectweb.asm.*;

/**
 * @author yujiangtao
 * @date 2018/7/23 15:19
 */
public class ClassPrinter extends ClassVisitor {

    public ClassPrinter() {
        super(Opcodes.ASM4);
    }

    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    @Override
    public void visitSource(String source, String debug) {
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return null;
    }

    @Override
    public void visitAttribute(Attribute attribute) {
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println("\t" + Type.getType(desc).getClassName() + " " + name);
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("    " + desc + " " + name);
        return null;
    }

    @Override
    public void visitEnd() {
        System.out.println("}");
    }
}
