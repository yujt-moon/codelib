package com.moon.asm.baeldung;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 字段添加适配器
 *
 * @author yujiangtao
 * @date 2020/8/28 下午5:27
 */
public class AddFieldAdapter extends ClassVisitor {

    private String fieldName;
    private String fieldDefault;
    private int access = Opcodes.ACC_PUBLIC;
    private String desc;
    private Object initValue;
    private boolean isFieldPresent;

    public AddFieldAdapter(String fieldName, int fieldAccess, String desc, ClassVisitor cv) {
        this(fieldName, fieldAccess, desc, null, cv);
    }

    public AddFieldAdapter(String fieldName, int fieldAccess, String desc, Object initValue, ClassVisitor cv) {
        super(Opcodes.ASM4, cv);

        assert fieldName != null;
        assert desc != null;
        assert cv != null;

        this.cv = cv;
        this.fieldName = fieldName;
        this.access = fieldAccess;
        this.desc = desc;
        this.initValue = initValue;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if(name.equals(fieldName)) {
            isFieldPresent = true;
        }
        return cv.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd() {
        if(!isFieldPresent) {
            FieldVisitor fv = cv.visitField(access, fieldName, desc, null, initValue);
            if(fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }
}
